package TableEntities;

public class HighGapStats {

	private String team;
	private int matchid;
	private int ownerID;
	private double teamPerformanceGap;
	
	public HighGapStats(String team, int matchid, int ownerID, double teamPerformanceGap) {
		super();
		this.team = team;
		this.matchid = matchid;
		this.ownerID = ownerID;
		this.teamPerformanceGap = teamPerformanceGap;
	}


	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		this.team = team;
	}


	public int getMatchid() {
		return matchid;
	}


	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}


	public int getOwnerID() {
		return ownerID;
	}


	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}


	public double getTeamPerformanceGap() {
		return teamPerformanceGap;
	}


	public void setTeamPerformanceGap(double teamPerformanceGap) {
		this.teamPerformanceGap = teamPerformanceGap;
	}


	@Override
	public String toString() {
		return "HighGapStats [getTeam()=" + getTeam() + ", getMatchid()=" + getMatchid() + ", getOwnerID()="
				+ getOwnerID() + ", getTeamPerformanceGap()=" + getTeamPerformanceGap() + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
