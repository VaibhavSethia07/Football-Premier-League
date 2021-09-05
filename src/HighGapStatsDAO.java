package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class HighGapStatsDAO {

	private Connection myConn;
	
	public HighGapStatsDAO() throws Exception {
		
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
	
	public List<HighGapStats> getAllHighGapStats() throws Exception {
		List<HighGapStats> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from\r\n" + 
					"(select teamname,matchid,ownerid,(max(score)-min(score))as Team_Perfomance_Gap from\r\n" + 
					"(select * from team natural join players natural join playermatchscore) as R1 group by teamname,matchid,ownerid)as R2 order by team_perfomance_gap limit 4;\r\n");
			
			while (myRs.next()) {
				HighGapStats tempGapsStats = convertRowToHighGapStats(myRs);
				list.add(tempGapsStats);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<HighGapStats> topGapStats(int top) throws Exception {
		List<HighGapStats> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.prepareStatement("select * from\r\n" + 
					"(select teamname,matchid,ownerid,(max(score)-min(score))as Team_Perfomance_Gap from\r\n" + 
					"(select * from team natural join players natural join playermatchscore) as R1 group by teamname,matchid,ownerid)as R2 order by team_perfomance_gap limit ?;\r\n");
			
			myStmt.setInt(1, top);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				HighGapStats tempGapStats = convertRowToHighGapStats(myRs);
				list.add(tempGapStats);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private HighGapStats convertRowToHighGapStats(ResultSet myRs) throws SQLException {
		
		String team = myRs.getString("teamname");
		int matchID = myRs.getInt("matchid");
		int ownerID = myRs.getInt("ownerid");
		double teamPerformanceGap = myRs.getDouble("team_perfomance_gap");
		
		HighGapStats tempGapStats = new HighGapStats(team,matchID,ownerID,teamPerformanceGap);
		
		return tempGapStats;
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
		
		HighGapStatsDAO dao = new HighGapStatsDAO();
		System.out.println(dao.topGapStats(10));
		
		System.out.println(dao.getAllHighGapStats());
	}
}

