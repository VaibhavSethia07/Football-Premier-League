package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class PlayerDAO {

	private Connection myConn;
	
	public PlayerDAO() throws Exception {
		
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
			myRs = myStmt.executeQuery("select * from players;");
			
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
	
	public List<Player> searchPlayers(String firstName) throws Exception {
		List<Player> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			firstName += "%";
			myStmt = myConn.prepareStatement("select * from players \r\n" + 
					"where playerid in \r\n" + 
					"(select playerid from teamplayers \r\n" + 
					"where (teamname,ownerid) in\r\n" + 
					"(select teamname,ownerid from team where ownerid in (select ownerid from owners where fname=? )));\r\n" + 
					"");
			
			myStmt.setString(1, firstName);
			
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
		
		PlayerDAO dao = new PlayerDAO();
		System.out.println(dao.searchPlayers("Harold"));

		System.out.println(dao.getAllPlayers());
	}
}
