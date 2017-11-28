package lucci.fa.data.objects;

public class Matchup {
	
	Team home;
	Team away;
	
	public Matchup(Team away, Team home) {
		this.away = away;
		this.home = home;
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}
}
