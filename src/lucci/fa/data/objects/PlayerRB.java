package lucci.fa.data.objects;

public class PlayerRB {
	
	String name;
	int attempts;
	int yards;
	double avg;
	int longest;
	int td;
	double ypg;
	
	public PlayerRB(String name, int attempts, int yards, double avg, int longest, int td, double ypg) {
		this.name = name;
		this.attempts = attempts;
		this.yards = yards;
		this.avg = avg;
		this.longest = longest;
		this.td = td;
		this.ypg = ypg;
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

	public int getLongest() {
		return longest;
	}

	public void setLongest(int longest) {
		this.longest = longest;
	}

	public int getTd() {
		return td;
	}

	public void setTd(int td) {
		this.td = td;
	}

	public double getYpg() {
		return ypg;
	}

	public void setYpg(double ypg) {
		this.ypg = ypg;
	}
}
