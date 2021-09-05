package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class TopPerformer {

	private Connection myConn;
	private int playerID;
	private String name;
	private String position;
	private String UEFATeam;
	private double score;
	
	public TopPerformer(int playerID, String name, String position, String uEFATeam, double score) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.position = position;
		UEFATeam = uEFATeam;
		this.score = score;
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

	public String getUEFATeam() {
		return UEFATeam;
	}

	public void setUEFATeam(String uEFATeam) {
		UEFATeam = uEFATeam;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
public TopPerformer() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("C:\\Users\\Vaibhav Sethia\\eclipse-workspace\\Football Premier League\\src\\properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}

	public List<TopPerformer> getTopPerformers() throws Exception {
		List<TopPerformer> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select playerid,name,position,uefateamname,score from players\r\n" + 
					"natural join\r\n" + 
					"(select playerid,PS.matchid,score from playermatchscore PS\r\n" + 
					"join  \r\n" + 
					"(select matchid,max(score)as MaxScore from playermatchscore group by matchid) as R on( R.MaxScore=PS.score and PS.matchid=R.matchid))as R2 order by score desc;\r\n" + 
					"");
			
			while (myRs.next()) {
				TopPerformer tempPlayer = convertRowToPlayerByTeam(myRs);
				list.add(tempPlayer);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private TopPerformer convertRowToPlayerByTeam(ResultSet myRs) throws SQLException {
		
		int playerID = myRs.getInt("playerid");
		String name = myRs.getString("name");
		String position = myRs.getString("position");
		String UEFATeam = myRs.getString("uefateamname");
		double score = myRs.getDouble("score");
		TopPerformer tempPlayer = new TopPerformer(playerID, name, position, UEFATeam, score);
		
		return tempPlayer;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	
	public String toString() {
		return String.format("Player [playerID=%s,name=%s, position=%s, UEFATeam=%s, score=%s]", playerID,name, position,UEFATeam,score);
	}


	public static void main(String[] args) throws Exception {
		
		TopPerformer dao = new TopPerformer();

		System.out.println(dao.getTopPerformers());
	}
}
