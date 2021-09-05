package TableEntities;

public class HighGap {
	private int matchid;
	private String UEFATeam;
	private double teamPerformanceGap;
	
	public HighGap(int matchid, String uEFATeam, double teamPerformanceGap) {
		super();
		this.matchid = matchid;
		UEFATeam = uEFATeam;
		this.teamPerformanceGap = teamPerformanceGap;
	}

	public int getMatchid() {
		return matchid;
	}

	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}

	public String getUEFATeam() {
		return UEFATeam;
	}

	public void setUEFATeam(String uEFATeam) {
		UEFATeam = uEFATeam;
	}

	public double getTeamPerformanceGap() {
		return teamPerformanceGap;
	}

	public void setTeamPerformanceGap(double teamPerformanceGap) {
		this.teamPerformanceGap = teamPerformanceGap;
	}

	@Override
	public String toString() {
		return "HighGap [getMatchid()=" + getMatchid() + ", getUEFATeam()=" + getUEFATeam()
				+ ", getTeamPerformanceGap()=" + getTeamPerformanceGap() + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
