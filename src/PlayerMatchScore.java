package TableEntities;

public class PlayerMatchScore {
	private int playerID;
	private int matchID;
	private double score;
	
	
	public PlayerMatchScore(int playerID, int matchID, double score) {
		super();
		this.playerID = playerID;
		this.matchID = matchID;
		this.score = score;
	}


	public int getPlayerID() {
		return playerID;
	}


	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}


	public int getMatchID() {
		return matchID;
	}


	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
