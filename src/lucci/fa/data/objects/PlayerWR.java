package lucci.fa.data.objects;

public class PlayerWR {
	
	String name;
	int receptions;
	int targets;
	int yards;
	double avg;
	int td;
	int longest;
	double ypg;
	int yac;
	
	public PlayerWR(String name, int receptions, int targets, int yards, double avg, int td, int longest, double ypg, int yac) {
		this.name = name;
		this.receptions = receptions;
		this.targets = targets;
		this.yards = yards;
		this.avg = avg;
		this.td = td;
		this.longest = longest;
		this.ypg = ypg;
		this.yac = yac;
	}

	public int getReceptions() {
		return receptions;
	}

	public void setReceptions(int receptions) {
		this.receptions = receptions;
	}

	public int getTargets() {
		return targets;
	}

	public void setTargets(int targets) {
		this.targets = targets;
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

	public int getTd() {
		return td;
	}

	public void setTd(int td) {
		this.td = td;
	}

	public int getLongest() {
		return longest;
	}

	public void setLongest(int longest) {
		this.longest = longest;
	}

	public double getYpg() {
		return ypg;
	}

	public void setYpg(double ypg) {
		this.ypg = ypg;
	}

	public int getYac() {
		return yac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYac(int yac) {
		this.yac = yac;
	}
}
