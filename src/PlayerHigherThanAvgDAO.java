package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class PlayerHigherThanAvgDAO {

	private Connection myConn;
	
	public PlayerHigherThanAvgDAO() throws Exception {
		
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
	
	public List<Player> getHighPlayers() throws Exception {
		List<Player> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from players PL where PL.playerid  not in \r\n" + 
					"(select playerid from playermatchscore PS\r\n" + 
					"join (select matchid,avg(score)as AverageScore from playermatchscore group by matchid) as R on (R.matchid=PS.matchid and PS.score < R.averagescore)) order by playerid; \r\n" + 
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
	
	public List<Player> searchPlayers(String teamName) throws Exception {
		List<Player> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			teamName += "%";
			myStmt = myConn.prepareStatement("select * from players PL where PL.playerid  not in \r\n" + 
					"(select playerid from playermatchscore PS\r\n" + 
					"join (select matchid,avg(score)as AverageScore from playermatchscore group by matchid) as R on (R.matchid=PS.matchid and PS.score < R.averagescore)) and uefateamname like ? order by playerid; \r\n" + 
					"");
			
			myStmt.setString(1, teamName);
			
			myRs = myStmt.executeQuery();
			
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
		
		PlayerHigherThanAvgDAO dao = new PlayerHigherThanAvgDAO();
		System.out.println(dao.searchPlayers("Real"));

		System.out.println(dao.getHighPlayers());
	}
}

