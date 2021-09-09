package lucci.fa.main;

import java.io.IOException;
import java.util.ArrayList;

import lucci.fa.data.objects.Matchup;
import lucci.fa.data.objects.Team;
import lucci.fa.utils.AbbreviationMap;
import lucci.fa.utils.FileWriter;
import lucci.fa.utils.Logger;
import lucci.fa.web.scraper.WebScraper;

public class FantasyAnalyzer {

	public static void main(String[] args) {
		// initialize logger **NOT IMPLEMENTED CURRENTLY
		String logFilePath = "";
		Logger logger = new Logger(logFilePath);
		
		// initialize NFL teams
		AbbreviationMap am = new AbbreviationMap();
		ArrayList<Team> teams = new ArrayList<Team>();
		teams.add(new Team("New England", "NE", "Patriots"));
		teams.add(new Team("Dallas", "Dal", "Cowboys"));
		teams.add(new Team("Las Vegas", "LV", "Raiders"));
		teams.add(new Team("Philadelphia", "Phi", "Eagles"));
		teams.add(new Team("New York", "NYG", "Giants"));
		teams.add(new Team("Seattle", "Sea", "Seahawks"));
		teams.add(new Team("Pittsburgh", "Pit", "Steelers"));
		teams.add(new Team("Green Bay", "GB", "Packers"));
		teams.add(new Team("Denver", "Den", "Broncos"));
		teams.add(new Team("San Francisco", "SF", "49ers"));
		teams.add(new Team("Chicago", "Chi", "Bears"));
		teams.add(new Team("Minnesota", "Min", "Vikings"));
		teams.add(new Team("Carolina", "Car", "Panthers"));
		teams.add(new Team("Cleveland", "Cle", "Browns"));
		teams.add(new Team("Los Angeles", "LAR", "Rams"));
		teams.add(new Team("Kansas City", "KC", "Chiefs"));
		teams.add(new Team("Washington", "Wsh", "Redskins"));
		teams.add(new Team("Atlanta", "Atl", "Falcons"));
		teams.add(new Team("Baltimore", "Bal", "Ravens"));
		teams.add(new Team("Houston", "Hou", "Texans"));
		teams.add(new Team("Detroit", "Det", "Lions"));
		teams.add(new Team("New York", "NYJ", "Jets"));
		teams.add(new Team("Los Angeles", "LAC", "Chargers"));
		teams.add(new Team("Arizona", "Ari", "Cardinals"));
		teams.add(new Team("Buffalo", "Buf", "Bills"));
		teams.add(new Team("New Orleans", "NO", "Saints"));
		teams.add(new Team("Miami", "Mia", "Dolphins"));
		teams.add(new Team("Jacksonville", "Jax", "Jaguars"));
		teams.add(new Team("Cincinnati", "Cin", "Bengals"));
		teams.add(new Team("Tampa Bay", "TB", "Buccaneers"));
		teams.add(new Team("Indianapolis", "Ind", "Colts"));
		teams.add(new Team("Tennessee", "Ten", "Titans"));
		
		WebScraper scraper = new WebScraper(logger);
		
		// grab record and top players
		for (Team team : teams) {
			try {
				scraper.initializeRecordAndTopPlayerURL("http://www.espn.com/nfl/team/stats/_/name/" + team.getAbbrev() + '/' + team.getCity() + '-' + team.getName());
			} catch(IOException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
			scraper.populateRecord(team);
			scraper._populateTopPlayers(team);
		}
		scraper.destroyRecordAndTopPlayerURL();
		
		// initialize URLs for the logic after
		try {
			scraper.initializeOverallRankURLs("https://www.espn.com/nfl/stats/team/_/table/passing/sort/netPassingYardsPerGame/dir/desc",
					"https://www.espn.com/nfl/stats/team/_/view/defense/table/passing/sort/netPassingYardsPerGame/dir/asc",
					"https://www.espn.com/nfl/stats/team/_/table/rushing/sort/rushingYardsPerGame/dir/desc",
					"https://www.espn.com/nfl/stats/team/_/view/defense/table/rushing/sort/rushingYardsPerGame/dir/asc");
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		// grab various ranks
		for (Team team : teams) {
			scraper.populateOverallRank(team);
		}
		scraper.destroyOverallRankURLs();
		
		// initialize URLs for the logic after
		try {
			scraper.initializePointsGivenToPositionURLS("https://football.fantasysports.yahoo.com/f1/pointsagainst?season=2020&pos=QB&mode=average",
					"https://football.fantasysports.yahoo.com/f1/pointsagainst?season=2020&pos=RB&mode=average",
					"https://football.fantasysports.yahoo.com/f1/pointsagainst?season=2020&pos=WR&mode=average",
					"https://football.fantasysports.yahoo.com/f1/pointsagainst?season=2020&pos=TE&mode=average",
					"https://football.fantasysports.yahoo.com/f1/pointsagainst?season=2020&pos=K&mode=average",
					"https://football.fantasysports.yahoo.com/f1/pointsagainst?season=2020&pos=DEF&mode=average");
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		// ArrayList containing matchups
		ArrayList<Matchup> matchups = new ArrayList<Matchup>();
		Matchup matchup = null;

		try {
			scraper.initializeMatchupsURL("https://www.espn.com/nfl/schedule");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		// populate fantasy points allowed to each position and create match ups
		for (Team team : teams) {
			scraper.populatePointsGivenToQB(team);
			scraper.populatePointsGivenToRB(team);
			scraper.populatePointsGivenToWR(team);
			scraper.populatePointsGivenToTE(team);
			scraper.populatePointsGivenToK(team);
			scraper.populatePointsGivenToDef(team);
			matchup = scraper._createMatchup(team, teams, am);
			
			if (matchup != null) {
				matchups.add(matchup);
			}
		}
		scraper.destroyPointsGivenToPositionURLS();
		scraper.destroyMatchupsURL();

		try {
			FileWriter.writeXLSXFile(matchups);
		} catch (IOException e) {
			// Using full stack trace for more precise error tracing regarding file writing
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Done! File should be created at desired location.");
	}
}