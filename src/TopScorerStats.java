package TableEntities;

public class TopScorerStats {
	
	private int playerID;
	private double averageGoalScore;
	private double averageScore;
	
	public TopScorerStats(int playerID, double averageGoalScore, double averageScore) {
		super();
		this.playerID = playerID;
		this.averageGoalScore = averageGoalScore;
		this.averageScore = averageScore;
	}
	
	public int getPlayerID() {
		return playerID;
	}



	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}



	public double getAverageGoalScore() {
		return averageGoalScore;
	}



	public void setAverageGoalScore(double averageGoalScore) {
		this.averageGoalScore = averageGoalScore;
	}



	public double getAverageScore() {
		return averageScore;
	}



	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}


	@Override
	public String toString() {
		return "TopScorerStats [getPlayerID()=" + getPlayerID() + ", getAverageGoalScore()=" + getAverageGoalScore()
				+ ", getAverageScore()=" + getAverageScore() + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
