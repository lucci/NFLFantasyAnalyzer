package lucci.fa.data.objects;

import java.util.ArrayList;

@SuppressWarnings({"rawtypes","unchecked"})
public class Team {

	String city;
	String name;
	String abbrev;
	int win;
	int loss;
	int oRushRank;
	int oPassRank;
	int dRushRank;
	int dPassRank;
	int fpToQBRank;
	double fpToQBAvg;
	int fpToRBRank;
	double fpToRBAvg;
	int fpToWRRank;
	double fpToWRAvg;
	int fpToTERank;
	double fpToTEAvg;
	int fpToKRank;
	double fpToKAvg;
	int fpToDefRank;
	double fpToDefAvg;
	PlayerQB startingQB;
	boolean matchedUp;
	ArrayList topRBs = new ArrayList<PlayerRB>();
	ArrayList topWRs = new ArrayList<PlayerWR>();

	public Team(String city, String abbrev, String name) {
		this.city = city;
		this.name = name;
		this.abbrev = abbrev;
		this.matchedUp = false;
	}
	
	public double getFpToQBAvg() {
		return fpToQBAvg;
	}

	public void setFpToQBAvg(double fpToQBAvg) {
		this.fpToQBAvg = fpToQBAvg;
	}

	public double getFpToRBAvg() {
		return fpToRBAvg;
	}

	public void setFpToRBAvg(double fpToRBAvg) {
		this.fpToRBAvg = fpToRBAvg;
	}

	public double getFpToWRAvg() {
		return fpToWRAvg;
	}

	public void setFpToWRAvg(double fpToWRAvg) {
		this.fpToWRAvg = fpToWRAvg;
	}

	public double getFpToTEAvg() {
		return fpToTEAvg;
	}

	public void setFpToTEAvg(double fpToTEAvg) {
		this.fpToTEAvg = fpToTEAvg;
	}

	public double getFpToKAvg() {
		return fpToKAvg;
	}

	public void setFpToKAvg(double fpToKAvg) {
		this.fpToKAvg = fpToKAvg;
	}

	public double getFpToDefAvg() {
		return fpToDefAvg;
	}

	public void setFpToDefAvg(double fpToDefAvg) {
		this.fpToDefAvg = fpToDefAvg;
	}

	public int getFpToQBRank() {
		return fpToQBRank;
	}

	public void setFpToQBRank(int fpToQBRank) {
		this.fpToQBRank = fpToQBRank;
	}

	public int getFpToRBRank() {
		return fpToRBRank;
	}

	public void setFpToRBRank(int fpToRBRank) {
		this.fpToRBRank = fpToRBRank;
	}

	public int getFpToWRRank() {
		return fpToWRRank;
	}

	public void setFpToWRRank(int fpToWRRank) {
		this.fpToWRRank = fpToWRRank;
	}

	public int getFpToTERank() {
		return fpToTERank;
	}

	public void setFpToTERank(int fpToTERank) {
		this.fpToTERank = fpToTERank;
	}

	public int getFpToKRank() {
		return fpToKRank;
	}

	public void setFpToKRank(int fpToKRank) {
		this.fpToKRank = fpToKRank;
	}

	public int getFpToDefRank() {
		return fpToDefRank;
	}

	public void setFpToDefRank(int fpToDefRank) {
		this.fpToDefRank = fpToDefRank;
	}
	
	public ArrayList<PlayerRB> getTopRBs() {
		return topRBs;
	}

	public void setTopRBs(ArrayList<PlayerRB> topRBs) {
		this.topRBs = topRBs;
	}
	
	public void addTopRB(PlayerRB rb) {
		this.topRBs.add(rb);
	}
	
	public ArrayList<PlayerWR> getTopWRs() {
		return topWRs;
	}

	public void setTopWRs(ArrayList<PlayerWR> topWRs) {
		this.topWRs = topWRs;
	}
	
	public void addTopWR(PlayerWR wr) {
		this.topWRs.add(wr);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public PlayerQB getStartingQB() {
		return startingQB;
	}

	public void setStartingQB(PlayerQB startingQB) {
		this.startingQB = startingQB;
	}

	public int getoRushRank() {
		return oRushRank;
	}

	public void setoRushRank(int oRushRank) {
		this.oRushRank = oRushRank;
	}

	public int getoPassRank() {
		return oPassRank;
	}

	public void setoPassRank(int oPassRank) {
		this.oPassRank = oPassRank;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public int getdRushRank() {
		return dRushRank;
	}

	public void setdRushRank(int dRushRank) {
		this.dRushRank = dRushRank;
	}

	public int getdPassRank() {
		return dPassRank;
	}

	public void setdPassRank(int dPassRank) {
		this.dPassRank = dPassRank;
	}

	public boolean isMatchedUp() {
		return matchedUp;
	}

	public void setMatchedUp(boolean matchedUp) {
		this.matchedUp = matchedUp;
	}
}
