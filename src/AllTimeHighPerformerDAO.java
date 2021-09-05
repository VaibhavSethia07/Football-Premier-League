package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class AllTimeHighPerformerDAO {

	private Connection myConn;
	private int playerID;
	private String name;
	private String position;
	private String UEFATeam;
	
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

	public AllTimeHighPerformerDAO() throws Exception {
		
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
	
	public List<Player> getAllPlayers() throws Exception {
		List<Player> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from players \r\n" + 
					"natural join(\r\n" + 
					"select playerid from (select playerid,sum(score)as all_time_score from playermatchscore group by playerid)as R2 \r\n" + 
					"where R2.all_time_score in\r\n" + 
					"(select max(all_time_score)as Max_all_time_score from (\r\n" + 
					"select playerid,sum(score)as all_time_score from playermatchscore group by playerid)as R))as R4;\r\n" + 
					"");
			
			while (myRs.next()) {
				Player tempPlayer = convertRowToPlayer(myRs);
				list.add(tempPlayer);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private Player convertRowToPlayer(ResultSet myRs) throws SQLException {
		
		int playerID = myRs.getInt("playerid");
		String name = myRs.getString("name");
		String position = myRs.getString("position");
		String UEFATeamName = myRs.getString("uefateamname");
		
		Player tempPlayer = new Player(playerID,name,position,UEFATeamName);
		
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

	public static void main(String[] args) throws Exception {
		
		AllTimeHighPerformerDAO dao = new AllTimeHighPerformerDAO();

		System.out.println(dao.getAllPlayers());
	}
}
