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
		teams.add(new Team("Oakland", "Oak", "Raiders"));
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
			scraper.populateTopPlayers(team);
		}
		scraper.destroyRecordAndTopPlayerURL();
		
		// initialize URLs for the logic after
		try {
			scraper.initializeOverallRankURLs("http://www.espn.com/nfl/statistics/team/_/stat/passing", 
					"http://www.espn.com/nfl/statistics/team/_/stat/passing/position/defense", 
					"http://www.espn.com/nfl/statistics/team/_/stat/rushing", 
					"http://www.espn.com/nfl/statistics/team/_/stat/rushing/position/defense");
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
			scraper.initializePointsGivenToPositionURLS("http://games.espn.com/ffl/pointsagainst?positionId=1&statview=averages",
					"http://games.espn.com/ffl/pointsagainst?positionId=2&statview=averages",
					"http://games.espn.com/ffl/pointsagainst?positionId=3&statview=averages",
					"http://games.espn.com/ffl/pointsagainst?positionId=4&statview=averages",
					"http://games.espn.com/ffl/pointsagainst?positionId=5&statview=averages",
					"http://games.espn.com/ffl/pointsagainst?positionId=16&statview=averages");
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		// ArrayList containing matchups
		ArrayList<Matchup> matchups = new ArrayList<Matchup>();
		Matchup matchup = null;
		
		// populate fantasy points allowed to each position
		for (Team team : teams) {
			scraper.populatePointsGivenToQB(team);
			scraper.populatePointsGivenToRB(team);
			scraper.populatePointsGivenToWR(team);
			scraper.populatePointsGivenToTE(team);
			scraper.populatePointsGivenToK(team);
			scraper.populatePointsGivenToDef(team);
			matchup = scraper.createMatchup(team, teams, am);
			
			if (matchup != null) {
				matchups.add(matchup);
			}
		}
		scraper.destroyPointsGivenToPositionURLS();
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