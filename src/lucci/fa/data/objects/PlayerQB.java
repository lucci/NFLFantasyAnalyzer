package lucci.fa.data.objects;

public class PlayerQB {
	
	String name;
	int attempts;
	int completions;
	int yards;
	double avg;
	double ypg;
	int td;
	int picks;
	
	public PlayerQB(String name, int attempts, int completions, int yards, double avg, double ypg, int td, int picks) {
		this.name = name;
		this.attempts = attempts;
		this.completions = completions;
		this.yards = yards;
		this.avg = avg;
		this.ypg = ypg;
		this.td = td;
		this.picks = picks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getCompletions() {
		return completions;
	}

	public void setCompletions(int completions) {
		this.completions = completions;
	}

	public int getYards() {
		return yards;
	}

	public void setYards(int yards) {
		this.yards = yards;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getYpg() {
		return ypg;
	}

	public void setYpg(double ypg) {
		this.ypg = ypg;
	}

	public int getTd() {
		return td;
	}

	public void setTd(int td) {
		this.td = td;
	}

	public int getPicks() {
		return picks;
	}

	public void setPicks(int picks) {
		this.picks = picks;
	}

}
