package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class PlayerStatsDAO {

	private Connection myConn;
	
	public PlayerStatsDAO() throws Exception {
		
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
	
	public String getGoldenBootPlayer() throws Exception {
		List<Player> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select name from players\r\n" + 
					"natural join(\r\n" + 
					"select playerid from (\r\n" + 
					"select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R2\r\n" + 
					"where R2.no_of_goals  \r\n" + 
					"in (select max(no_of_goals) as max_no_of_goals from (\r\n" + 
					"select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R))as R3;\r\n" + 
					"");
			
			while (myRs.next()) {
				return myRs.getString("name");
			}
		}
		finally {
			close(myStmt, myRs);
		}
		
		return "";
	}
	
	
	private Player convertRowToPlayer(ResultSet myRs) throws SQLException {
		int playerID = myRs.getInt("playerid");
		String name = myRs.getString("name");
		String position = myRs.getString("position");
		String UEFATeamName = myRs.getString("uefateamname");
		
		Player tempPlayer = new Player(playerID=0,position="",UEFATeamName="",name);
		
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
		
		PlayerStatsDAO dao = new PlayerStatsDAO();

		System.out.println(dao.getGoldenBootPlayer());
	}
}
