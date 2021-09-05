package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class TopScorerDAO {

	private Connection myConn;
	
	public TopScorerDAO() throws Exception {
		
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
	
	public List<TopScorer> getAllPlayers() throws Exception {
		List<TopScorer> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from \r\n" + 
					"(select playerid,matchid from playermatchscore PMS \r\n" + 
					"where \r\n" + 
					"(PMS.matchid,PMS.score)\r\n" + 
					"in\r\n" + 
					"(select matchid,max(score) from playermatchscore PS group by matchid)) as R1 natural join players order by matchid;\r\n" + 
					"");
			
			while (myRs.next()) {
				TopScorer tempPlayer = convertRowToPlayer(myRs);
				list.add(tempPlayer);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private TopScorer convertRowToPlayer(ResultSet myRs) throws SQLException {
		
		int playerID = myRs.getInt("playerid");
		int matchID = myRs.getInt("matchid");
		String name = myRs.getString("name");
		String position = myRs.getString("position");
		String UEFATeamName = myRs.getString("uefateamname");
		
		TopScorer tempPlayer = new TopScorer(playerID,matchID,name,position,UEFATeamName);
		
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
		
		TopScorerDAO dao = new TopScorerDAO();
		
		System.out.println(dao.getAllPlayers());
	}
}

