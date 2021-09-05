package TableEntities;

public class TopScorer {
	private int playerid;
	private int matchid;
	private String name;
	private String position;
	private String UEFATeam;
	
	public TopScorer(int playerid, int matchid, String name, String position, String uEFATeam) {
		super();
		this.playerid = playerid;
		this.matchid = matchid;
		this.name = name;
		this.position = position;
		UEFATeam = uEFATeam;
	}

	public int getPlayerid() {
		return playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public int getMatchid() {
		return matchid;
	}

	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUEFATeam() {
		return UEFATeam;
	}

	public void setUEFATeam(String uEFATeam) {
		UEFATeam = uEFATeam;
	}

	@Override
	public String toString() {
		return "TopScorer [getPlayerid()=" + getPlayerid() + ", getMatchid()=" + getMatchid() + ", getName()="
				+ getName() + ", getPosition()=" + getPosition() + ", getUEFATeam()=" + getUEFATeam() + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
