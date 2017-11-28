package lucci.fa.web.scraper;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lucci.fa.data.objects.Matchup;
import lucci.fa.data.objects.PlayerQB;
import lucci.fa.data.objects.PlayerRB;
import lucci.fa.data.objects.PlayerWR;
import lucci.fa.data.objects.Team;
import lucci.fa.utils.AbbreviationMap;
import lucci.fa.utils.Logger;

public class WebScraper {
	
	Logger logger;
	Document recAndPlayerURL;
	Document oPassingURL;
	Document dPassingURL;
	Document oRushingURL;
	Document dRushingURL;
	Document qbPointsAllowedURL;
	Document rbPointsAllowedURL;
	Document wrPointsAllowedURL;
	Document tePointsAllowedURL;
	Document kPointsAllowedURL;
	Document defPointsAllowedURL;
	
	public WebScraper(Logger logger) {
		this.logger = logger;
		recAndPlayerURL = null;
		oPassingURL = null;
		dPassingURL = null;
		oRushingURL = null;
		dRushingURL = null;
		qbPointsAllowedURL = null;
		rbPointsAllowedURL = null;
		wrPointsAllowedURL = null;
		tePointsAllowedURL = null;
		kPointsAllowedURL = null;
		defPointsAllowedURL = null;
	}
	
	// initializes and stores URL content for processing
	public void initializeRecordAndTopPlayerURL(String recAndPlayerURL) {
		try {
			this.recAndPlayerURL = Jsoup.connect(recAndPlayerURL).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// destroys URL content and invokes the garbage collector
	public void destroyRecordAndTopPlayerURL() {
		recAndPlayerURL = null;
		System.gc();
	}
	
	// populates the teams record
	public void populateRecord(Team team) {
		Elements recordResults = recAndPlayerURL.select("div[class=sub-title]");
		String record = recordResults.get(0).text();
		team.setWin(Integer.parseInt(record.substring(0, record.indexOf('-'))));
		team.setLoss(Integer.parseInt(record.substring(record.indexOf('-') + 1, record.indexOf(','))));
	}
	
	// populates the top players for the team
	public void populateTopPlayers(Team team) {
		// Begin populating QB
		Element tableQB = recAndPlayerURL.select("table").get(0);
	    Elements rowsQB = tableQB.select("tr");
	  
	    for (int i = 2; i < 3; i++) {
	        Element row = rowsQB.get(i);
	        Elements cols = row.select("td");
	        
	        if(cols.get(0).text().equalsIgnoreCase("totals")) {
	        		break;
	        }
	        String name = cols.get(0).text();
	        int attempts = Integer.parseInt(cols.get(1).text());
		    	int completions = Integer.parseInt(cols.get(2).text());
		    	int yards = Integer.parseInt(cols.get(4).text());
		    	double avg = Double.parseDouble(cols.get(5).text());
		    	double ypg = Double.parseDouble(cols.get(6).text());;
		    	int td = Integer.parseInt(cols.get(8).text());
		    	int picks = Integer.parseInt(cols.get(10).text());
		    	team.setStartingQB(new PlayerQB(name, attempts, completions, yards, avg, ypg, td, picks));
	    }
				    
		// Begin populating RB
		Element tableRB = recAndPlayerURL.select("table").get(1);
	    Elements rowsRB = tableRB.select("tr");
	  
	    for (int i = 2; i < rowsRB.size(); i++) {
	        Element row = rowsRB.get(i);
	        Elements cols = row.select("td");
	        
	        if(cols.get(0).text().equalsIgnoreCase("totals")) {
	        		break;
	        }
	        String name = cols.get(0).text();
	        int attempts = Integer.parseInt(cols.get(1).text());
		    	int yards = Integer.parseInt(cols.get(2).text());
		    	double avg = Double.parseDouble(cols.get(3).text());
		    	int longest = Integer.parseInt(cols.get(4).text());
		    	int td = Integer.parseInt(cols.get(6).text());
		    	double ypg = Double.parseDouble(cols.get(7).text());
	        team.addTopRB(new PlayerRB(name, attempts, yards, avg, longest, td, ypg));
	    }
	    
	    // Begin populating WR
		Element tableWR = recAndPlayerURL.select("table").get(2);
	    Elements rowsWR = tableWR.select("tr");
	  
	    for (int i = 2; i < rowsWR.size(); i++) {
	        Element row = rowsWR.get(i);
	        Elements cols = row.select("td");
	        
	        if(cols.get(0).text().equalsIgnoreCase("totals")) {
	        		break;
	        }
	        String name = cols.get(0).text();
	        int receptions = Integer.parseInt(cols.get(1).text());
	        int targets = Integer.parseInt(cols.get(2).text());
	        int yards = Integer.parseInt(cols.get(3).text());
	        double avg = Double.parseDouble(cols.get(4).text());
 		 	int td = Integer.parseInt(cols.get(5).text());
 		 	int longest = Integer.parseInt(cols.get(6).text());
 		 	double ypg = Double.parseDouble(cols.get(8).text());
 		 	int yac = Integer.parseInt(cols.get(11).text());
 		 	team.addTopWR(new PlayerWR(name, receptions, targets, yards, avg, td, longest, ypg, yac));
	    }
	}
	
	// initializes and stores URL content for processing
	public void initializeOverallRankURLs(String OPassingURL, String DPassingURL, String ORushingURL, String DRushingURL) {
		try {
			oPassingURL = Jsoup.connect(OPassingURL).get();
			dPassingURL = Jsoup.connect(DPassingURL).get();
			oRushingURL = Jsoup.connect(ORushingURL).get();
			dRushingURL = Jsoup.connect(DRushingURL).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// destroys URL content and invokes the garbage collector
	public void destroyOverallRankURLs() {
		oPassingURL = null;
		dPassingURL = null;
		oRushingURL = null;
		dRushingURL = null;
		System.gc();
	}
	
	// populates various ranks for the team
	public void populateOverallRank(Team team) {
		
		// Begin populating passing offense rank
		Element oPassingTable = oPassingURL.select("table").get(0);
	    Elements oPassingRows = oPassingTable.select("tr");
	    int rank = 0;
	    
	    for (int i = 1; i < oPassingRows.size(); i++) {
	    		Element row = oPassingRows.get(i);
	    		Elements cols = row.select("td");
	    		rank++;
	    		
	    		if(cols.get(1).text().trim().equalsIgnoreCase(team.getCity()) || cols.get(1).text().trim().equalsIgnoreCase(team.getAbbrev().substring(0,2) + " " + team.getName())) {
	    			team.setoPassRank(rank);
	    			rank = 0;
	    			break;
	    		}
	    }
	    
	    // Begin populating passing defense rank
	    Element dPassingTable = dPassingURL.select("table").get(0);
	    Elements dPassingRows = dPassingTable.select("tr");
	    
	    for (int i = 1; i < dPassingRows.size(); i++) {
	    		Element row = dPassingRows.get(i);
	    		Elements cols = row.select("td");
	    		rank++;
	    		
	    		if(cols.get(1).text().trim().equalsIgnoreCase(team.getCity()) || cols.get(1).text().trim().equalsIgnoreCase(team.getAbbrev().substring(0,2) + " " + team.getName())) {
	    			//team.setdPassRank(Integer.parseInt(cols.get(0).text()));
	    			team.setdPassRank(rank);
	    			rank = 0;
	    			break;
	    		}
	    }
	    
	    // Begin populating rushing offense rank
	    Element oRushingTable = oRushingURL.select("table").get(0);
	    Elements oRushingRows = oRushingTable.select("tr");
	    
	    for (int i = 1; i < oRushingRows.size(); i++) {
	    		Element row = oRushingRows.get(i);
	    		Elements cols = row.select("td");
	    		rank++;
	    		
	    		if(cols.get(1).text().trim().equalsIgnoreCase(team.getCity()) || cols.get(1).text().trim().equalsIgnoreCase(team.getAbbrev().substring(0,2) + " " + team.getName())) {
	    			team.setoRushRank(rank);
	    			rank = 0;
	    			break;
	    		}
	    }
	    
	    // Begin populating rushing defense rank
	    Element dRushingTable = dRushingURL.select("table").get(0);
	    Elements dRushingRows = dRushingTable.select("tr");
	    
	    for (int i = 1; i < dRushingRows.size(); i++) {
	    		Element row = dRushingRows.get(i);
	    		Elements cols = row.select("td");
	    		rank++;
	    		
	    		if(cols.get(1).text().trim().equalsIgnoreCase(team.getCity()) || cols.get(1).text().trim().equalsIgnoreCase(team.getAbbrev().substring(0,2) + " " + team.getName())) {
	    			team.setdRushRank(rank);
	    			rank = 0;
	    			break;
	    		}
	    }
	}
	
	// initializes and stores URL content for processing
	public void initializePointsGivenToPositionURLS(String qbURL, String rbURL, String wrURL, String teURL, String kURL, String dURL) {
		try {
			qbPointsAllowedURL = Jsoup.connect(qbURL).get();
			rbPointsAllowedURL = Jsoup.connect(rbURL).get();
			wrPointsAllowedURL = Jsoup.connect(wrURL).get();
			tePointsAllowedURL = Jsoup.connect(teURL).get();
			kPointsAllowedURL = Jsoup.connect(kURL).get();
			defPointsAllowedURL = Jsoup.connect(dURL).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// destroys URL content and invokes garbage collector
	public void destroyPointsGivenToPositionURLS() {
		qbPointsAllowedURL = null;
		rbPointsAllowedURL = null;
		wrPointsAllowedURL = null;
		tePointsAllowedURL = null;
		kPointsAllowedURL = null;
		defPointsAllowedURL = null;
		System.gc();
	}
	
	// populates fantasy points given to QBs
	public void populatePointsGivenToQB(Team team) {
		int rankCounter = 0;
		Element qbPointsAllowedTable = qbPointsAllowedURL.select("table").get(1);
	    Elements qbPointsAllowedRows = qbPointsAllowedTable.select("tr");
	    
	    for (int i = 2; i < qbPointsAllowedRows.size(); i++) {
	    		Element row = qbPointsAllowedRows.get(i);
	    		Elements cols = row.select("td");
	    		rankCounter++;
	    		
	    		if(cols.get(0).text().contains(team.getName())) {
	    			if (cols.get(2).text().contains("*")) {
	    				team.setFpToQBRank(rankCounter);
		    			team.setFpToQBAvg(Double.parseDouble(cols.get(18).text()));
		    			break;
	    			} else {
		    			team.setFpToQBRank(rankCounter);
		    			team.setFpToQBAvg(Double.parseDouble(cols.get(19).text()));
		    			break;
		    		}
	    		}
    		}
	}
	
	// populates fantasy points given to RBs
	public void populatePointsGivenToRB(Team team) {
		int rankCounter = 0;
  		Element rbPointsAllowedTable = rbPointsAllowedURL.select("table").get(1);
  	    Elements rbPointsAllowedRows = rbPointsAllowedTable.select("tr");
  	    
  	    for (int i = 2; i < rbPointsAllowedRows.size(); i++) {
  	    		Element row = rbPointsAllowedRows.get(i);
  	    		Elements cols = row.select("td");
  	    		rankCounter++;
      		
  	    		if(cols.get(0).text().contains(team.getName())) {
  	    			if (cols.get(2).text().contains("*")) {
	    				team.setFpToRBRank(rankCounter);
		    			team.setFpToRBAvg(Double.parseDouble(cols.get(18).text()));
		    			break;
	    			} else {
		    			team.setFpToRBRank(rankCounter);
		    			team.setFpToRBAvg(Double.parseDouble(cols.get(19).text()));
		    			break;
		    		}
  	    		}
  	    }
	}
	
	// populates fantasy points given to WRs
	public void populatePointsGivenToWR(Team team) {
		int rankCounter = 0;
  		Element wrPointsAllowedTable = wrPointsAllowedURL.select("table").get(1);
  	    Elements wrPointsAllowedRows = wrPointsAllowedTable.select("tr");
  	    
  	    for (int i = 2; i < wrPointsAllowedRows.size(); i++) {
  	    		Element row = wrPointsAllowedRows.get(i);
  	    		Elements cols = row.select("td");
  	    		rankCounter++;
      		
  	    		if(cols.get(0).text().contains(team.getName())) {
  	    			if (cols.get(2).text().contains("*")) {
	    				team.setFpToWRRank(rankCounter);
		    			team.setFpToWRAvg(Double.parseDouble(cols.get(18).text()));
		    			break;
	    			} else {
		    			team.setFpToWRRank(rankCounter);
		    			team.setFpToWRAvg(Double.parseDouble(cols.get(19).text()));
		    			break;
		    		}
  	    		}
  	    }
	}
	
	// populates fantasy points given to TEs
	public void populatePointsGivenToTE(Team team) {
		int rankCounter = 0;
  		Element tePointsAllowedTable = tePointsAllowedURL.select("table").get(1);
  	    Elements tePointsAllowedRows = tePointsAllowedTable.select("tr");
  	    
  	    for (int i = 2; i < tePointsAllowedRows.size(); i++) {
  	    		Element row = tePointsAllowedRows.get(i);
  	    		Elements cols = row.select("td");
  	    		rankCounter++;
      		
  	    		if(cols.get(0).text().contains(team.getName())) {
  	    			if (cols.get(2).text().contains("*")) {
	    				team.setFpToTERank(rankCounter);
		    			team.setFpToTEAvg(Double.parseDouble(cols.get(18).text()));
		    			break;
	    			} else {
		    			team.setFpToTERank(rankCounter);
		    			team.setFpToTEAvg(Double.parseDouble(cols.get(19).text()));
		    			break;
		    		}
  	    		}
  	    }
	}
	
	// populates fantasy points gives to Ks
	public void populatePointsGivenToK(Team team) {
		int rankCounter = 0;
  		Element kPointsAllowedTable = kPointsAllowedURL.select("table").get(1);
  	    Elements kPointsAllowedRows = kPointsAllowedTable.select("tr");
  	    
  	    for (int i = 2; i < kPointsAllowedRows.size(); i++) {
  	    		Element row = kPointsAllowedRows.get(i);
  	    		Elements cols = row.select("td");
  	    		rankCounter++;
      		
  	    		if(cols.get(0).text().contains(team.getName())) {
  	    			if (cols.get(2).text().contains("*")) {
	    				team.setFpToKRank(rankCounter);
		    			team.setFpToKAvg(Double.parseDouble(cols.get(10).text()));
		    			break;
	    			} else {
		    			team.setFpToKRank(rankCounter);
		    			team.setFpToKAvg(Double.parseDouble(cols.get(11).text()));
		    			break;
		    		}
  	    		}
  	    }
	}
	
	// populates fantasy points given to DEFs
	public void populatePointsGivenToDef(Team team) {
		int rankCounter = 0;
  		Element defPointsAllowedTable = defPointsAllowedURL.select("table").get(0);
  	    Elements defPointsAllowedRows = defPointsAllowedTable.select("tr");
  	    
  	    for (int i = 3; i < defPointsAllowedRows.size(); i++) {
  	    		Element row = defPointsAllowedRows.get(i);
  	    		Elements cols = row.select("td");
  	    		rankCounter++;
      		
  	    		if(cols.get(0).text().contains(team.getName())) {
  	    			if (cols.get(2).text().contains("*")) {
	    				team.setFpToDefRank(rankCounter);
		    			team.setFpToDefAvg(Double.parseDouble(cols.get(18).text()));
		    			break;
	    			} else {
		    			team.setFpToDefRank(rankCounter);
		    			team.setFpToDefAvg(Double.parseDouble(cols.get(19).text()));
		    			break;
		    		}
  	    		}
  	    }
	}
	
	/*
	 * DEAD CODE
	public void createMatchups(AbbreviationMap abbrevMap) {
		Element matchupTable = qbPointsAllowedURL.select("table").get(1);
	    Elements matchupRows = matchupTable.select("tr");
	    
	    for (int i = 2; i < matchupRows.size(); i++) {
	    		boolean teamOneHome;
	    		Element row = matchupRows.get(i);
	    		Elements cols = row.select("td");
	    		String teamOne = cols.get(0).text().substring(0, cols.get(0).text().indexOf('.') - 3).trim();
	    		String teamTwo;
	    		String teamTwoTmp;
	    		String teamTwoRaw = cols.get(2).text();
	    		
	    		if (!teamTwoRaw.contains("BYE"))
	    		System.out.print(teamOne + " - ");
	    		
	    		if (teamTwoRaw.contains("@")) {
	    			teamOneHome = false;
	    			teamTwoTmp = teamTwoRaw.substring(teamTwoRaw.indexOf("@") + 1);
	    			teamTwo = abbrevMap.abbrevKey.get(teamTwoTmp.trim());
	    			System.out.println(teamTwo);
	    		} else if (!teamTwoRaw.contains("BYE")){
	    			teamOneHome = true;
	    			teamTwo = abbrevMap.abbrevKey.get(teamTwoRaw.trim());
	    			System.out.print(teamTwo + "\n");
	    		}
	    }
	}
	*/
	
	// creates the matchup object using teams parsed from the web; determines who is away/home
	public Matchup createMatchup(Team team, ArrayList<Team> teams, AbbreviationMap abbrevMap) {
		Matchup matchup = null;
		Element matchupTable = qbPointsAllowedURL.select("table").get(1);
	    Elements matchupRows = matchupTable.select("tr");
	    String teamTwo = null;
	    
	    if (!team.isMatchedUp()) {
		    for (int i = 2; i < matchupRows.size(); i++) {
		    		Element row = matchupRows.get(i);
		    		Elements cols = row.select("td");
		    		
		    		if (cols.get(0).text().contains(team.getName())) {
		    			if (!cols.get(2).text().contains("*")) {
		    				if (!cols.get(2).text().contains("@")) {
		    					teamTwo = abbrevMap.abbrevKey.get(cols.get(2).text().trim());
		    				} else {
		    					teamTwo = abbrevMap.abbrevKey.get(cols.get(2).text().trim().substring(1));
		    				}
		    			
		    				if (cols.get(2).text().contains("@")) {
		    					for (Team t : teams) {
		    						if (t.getCity().equalsIgnoreCase(teamTwo)) {
		    							team.setMatchedUp(true);
		    							t.setMatchedUp(true);
		    							matchup = new Matchup(team, t);
		    						}
		    					}
		    				} else {
		    					for (Team t : teams) {
		    						if (t.getCity().equalsIgnoreCase(teamTwo)) {
		    							team.setMatchedUp(true);
		    							t.setMatchedUp(true);
		    							matchup = new Matchup(t, team);
		    						}
		    					}
		    				}
		    			}
		    		}
		    }
	    }
	    return matchup;
	}
}