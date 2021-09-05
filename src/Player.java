package TableEntities;

public class Player {
	private int playerID;
	private String name;
	private String position;
	private String UEFATeamName;
	
	
	
	public Player(int playerID, String name, String position, String uEFATeamName) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.position = position;
		UEFATeamName = uEFATeamName;
	}



	public int getPlayerID() {
		return playerID;
	}



	public void setPlayerID(int playerID) {
		this.playerID = playerID;
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



	public String getUEFATeamName() {
		return UEFATeamName;
	}



	public void setUEFATeamName(String UEFATeamName) {
		UEFATeamName = UEFATeamName;
	}

	public String toString() {
		return String.format("Player [id=%s, name=%s, position=%s, UEFATeam=%s]", playerID, name, position, UEFATeamName);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
