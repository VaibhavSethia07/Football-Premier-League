package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class TopScorerStatsDAO {

	private Connection myConn;
	
	public TopScorerStatsDAO() throws Exception {
		
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
	
	public List<TopScorerStats> getAllStats() throws Exception {
		List<TopScorerStats> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from\r\n" + 
					"(select * from\r\n" + 
					"(select playerid,avg(shotsongoalratio) as avgGS,avg(score) as avgScore from\r\n" + 
					"(select * from\r\n" + 
					"(select playerid,matchid, (goals*100)/shots as ShotsOnGoalratio from playermatchperformance where shots>0)as R1\r\n" + 
					"natural join\r\n" + 
					"(select * from playermatchscore)as R2) as R3 group by playerid)as R4 where avgGS>0)as R4 order by avgscore desc ;\r\n" + 
					"");
			
			while (myRs.next()) {
				TopScorerStats tempStats = convertRowToStats(myRs);
				list.add(tempStats);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private TopScorerStats convertRowToStats(ResultSet myRs) throws SQLException {
		
		int playerID = myRs.getInt("playerid");
		double averageGoalScore = myRs.getDouble("avgGS");
		double averageScore = myRs.getDouble("avgScore");
		
		TopScorerStats tempStats = new TopScorerStats(playerID,averageGoalScore,averageScore);
		
		return tempStats;
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
		
		TopScorerStatsDAO dao = new TopScorerStatsDAO();
		System.out.println(dao.getAllStats());
	}
}
