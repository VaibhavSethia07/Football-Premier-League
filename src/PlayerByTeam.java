package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class PlayerByTeam {

	private Connection myConn;
	private String name;
	private double score;
	private int matchID;
	
	public PlayerByTeam(String name, double score, int matchID) {
		super();
		this.name = name;
		this.score = score;
		this.matchID = matchID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public PlayerByTeam() throws Exception {
		
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
	
	public List<PlayerByTeam> getAllPlayers() throws Exception {
		List<PlayerByTeam> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select name,score,matchid from playermatchscore \r\n" + 
					"natural join \r\n" + 
					"(select playerid,name from players \r\n" + 
					"where playerid in\r\n" + 
					"(select playerid from teamplayers)) as R;\r\n" + 
					"");
			
			while (myRs.next()) {
				PlayerByTeam tempPlayer = convertRowToPlayerByTeam(myRs);
				list.add(tempPlayer);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<PlayerByTeam> searchPlayers(String team,String UEFAteam) throws Exception {
		List<PlayerByTeam> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select name,score,matchid from playermatchscore \r\n" + 
					"natural join \r\n" + 
					"(select playerid,name from players \r\n" + 
					"where playerid in\r\n" + 
					"(select playerid from teamplayers where teamname=?) and uefateamname=?)as R;\r\n" + 
					"");
			
			myStmt.setString(1, team);
			myStmt.setString(2,  UEFAteam);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				PlayerByTeam tempPlayer = convertRowToPlayerByTeam(myRs);
				list.add(tempPlayer);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private PlayerByTeam convertRowToPlayerByTeam(ResultSet myRs) throws SQLException {
		
		String name = myRs.getString("name");
		double score = myRs.getDouble("score");
		int matchID = myRs.getInt("matchID");
		
		PlayerByTeam tempPlayer = new PlayerByTeam(name,score,matchID);
		
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
		return String.format("Player [name=%s, score=%s, matchID=%s]", name, score, matchID);
	}


	public static void main(String[] args) throws Exception {
		
		PlayerByTeam dao = new PlayerByTeam();
		System.out.println(dao.searchPlayers("Barce-loners","Real Madrid"));

		System.out.println(dao.getAllPlayers());
	}
}
