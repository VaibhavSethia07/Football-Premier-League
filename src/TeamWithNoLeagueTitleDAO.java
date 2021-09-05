package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class TeamWithNoLeagueTitleDAO {

	private Connection myConn;
	
	public TeamWithNoLeagueTitleDAO() throws Exception {
		
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
	
	public List<TeamWithNoLeagueTitle> getAllTeams() throws Exception {
		List<TeamWithNoLeagueTitle> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from team T\r\n" + 
					"where\r\n" + 
					"(T.teamname,T.ownerid)\r\n" + 
					"not in \r\n" + 
					"(select R2.teamname,R2.ownerid from (select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R2\r\n" + 
					"join \r\n" + 
					"(select leaguename,leaguecommissionerid,max(leaguescore)as MaxLeagueScore from(\r\n" + 
					"select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R group by leaguename,leaguecommissionerid) as R3 on (R3.MaxLeagueScore=R2.LeagueScore and R2.leaguename=R3.leaguename and R2.leaguecommissionerid=R3.leaguecommissionerid));\r\n" + 
					"");
			
			while (myRs.next()) {
				TeamWithNoLeagueTitle tempTeam = convertRowToTeam(myRs);
				list.add(tempTeam);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	private TeamWithNoLeagueTitle convertRowToTeam(ResultSet myRs) throws SQLException {
		
		String teamName = myRs.getString("teamname");
		String location = myRs.getString("location");
		int ownerID = myRs.getInt("ownerid");
		
		TeamWithNoLeagueTitle tempTeam = new TeamWithNoLeagueTitle(teamName,location,ownerID);
		
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
		
		TeamWithNoLeagueTitleDAO dao = new TeamWithNoLeagueTitleDAO();

		System.out.println(dao.getAllTeams());
	}
}
