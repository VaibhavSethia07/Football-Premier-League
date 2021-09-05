package TableEntities;

public class LeagueWinner {
	
	private String teamName;
	private String leagueName;
	private int leagueScore;
	
	public LeagueWinner(String teamName, String leagueName, int leagueScore) {
		super();
		this.teamName = teamName;
		this.leagueName = leagueName;
		this.leagueScore = leagueScore;
	}


	public String getTeamName() {
		return teamName;
	}



	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}



	public String getLeagueName() {
		return leagueName;
	}



	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}



	public int getLeagueScore() {
		return leagueScore;
	}



	public void setLeagueScore(int leagueScore) {
		this.leagueScore = leagueScore;
	}

	public String toString() {
		return String.format("League Winner [Team Name=%s, League Name=%s, League Score=%s]", teamName, leagueName, leagueScore);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
