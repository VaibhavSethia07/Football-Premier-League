package TableEntities;

public class TeamWithNoLeagueTitle {
	private String teamname;
	private String location;
	private int ownerid;
	
	public TeamWithNoLeagueTitle(String teamname, String location, int ownerid) {
		super();
		this.teamname = teamname;
		this.location = location;
		this.ownerid = ownerid;
	}
	
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	
	@Override
	public String toString() {
		return "TeamWithNoLeagueTitle [getTeamname()=" + getTeamname() + ", getLocation()=" + getLocation()
				+ ", getOwnerid()=" + getOwnerid() + "]";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
