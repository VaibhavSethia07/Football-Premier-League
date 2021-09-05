package TableEntities;

public class TeamsHighPerformanceBallers {
	private String teamname;
	private int ownerID;
	private int matchID;
	private String league;
	private int leagueCommissionerID;
	private int score;
	
	public TeamsHighPerformanceBallers(String teamname, int ownerID, int matchID, String league,
			int leagueCommissionerID, int score) {
		super();
		this.teamname = teamname;
		this.ownerID = ownerID;
		this.matchID = matchID;
		this.league = league;
		this.leagueCommissionerID = leagueCommissionerID;
		this.score = score;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public int getLeagueCommissionerID() {
		return leagueCommissionerID;
	}

	public void setLeagueCommissionerID(int leagueCommissionerID) {
		this.leagueCommissionerID = leagueCommissionerID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "TeamsHighPerformanceBallers [getTeamname()=" + getTeamname() + ", getOwnerID()=" + getOwnerID()
				+ ", getMatchID()=" + getMatchID() + ", getLeague()=" + getLeague() + ", getLeagueCommissionerID()="
				+ getLeagueCommissionerID() + ", getScore()=" + getScore() + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
