package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class TeamsHighPerformanceBallersDAO {

	private Connection myConn;
	
	public TeamsHighPerformanceBallersDAO() throws Exception {
		
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
	
	public List<TeamsHighPerformanceBallers> getAllTeams() throws Exception {
		List<TeamsHighPerformanceBallers> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select R1.* from (\r\n" + 
					"select * from teamleaguematchperfomance TLP\r\n" + 
					"where (TLP.teamname,TLP.ownerid,TLP.matchid)\r\n" + 
					"in \r\n" + 
					"(select teamname,ownerid,matchid from(\r\n" + 
					"select matchid,uefateamname,teamname,ownerid,count(playerid)as NoOfPlayers from(\r\n" + 
					"select	* from teamplayers natural join players) as R group by matchid,uefateamname,teamname,ownerid)as R1 where noofplayers>=6))as R1\r\n" + 
					"natural join \r\n" + 
					"(select matchid,leaguename,leaguecommissionerid,avg(score) as AVGscore from teamleaguematchperfomance TLP group by matchid,leaguename,leaguecommissionerid)as R2 where R1.score > R2.avgscore;\r\n" + 
					"");
			
			while (myRs.next()) {
				TeamsHighPerformanceBallers tempTeams = convertRowToTeams(myRs);
				list.add(tempTeams);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private TeamsHighPerformanceBallers convertRowToTeams(ResultSet myRs) throws SQLException {
		
		String teamname = myRs.getString("teamname");
		int ownerID = myRs.getInt("ownerid");
		int matchid = myRs.getInt("matchid");
		String leaguename = myRs.getString("leaguename");
		int leagueCommissionerID = myRs.getInt("leaguecommissionerid");
		int score = myRs.getInt("score");
		
		TeamsHighPerformanceBallers tempTeam = new TeamsHighPerformanceBallers(teamname,ownerID,matchid,leaguename,leagueCommissionerID,score);
		
		return tempTeam;
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
		
		TeamsHighPerformanceBallersDAO dao = new TeamsHighPerformanceBallersDAO();

		System.out.println(dao.getAllTeams());
	}
}
