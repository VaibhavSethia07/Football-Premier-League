package TableEntities;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LeagueWinnerDAO {

	private Connection myConn;
	
	public LeagueWinnerDAO() throws Exception {
		
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
	
	public List<LeagueWinner> getAllPlayers() throws Exception {
		List<LeagueWinner> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select R2.* from (select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R2\r\n" + 
					"join \r\n" + 
					"(select leaguename,leaguecommissionerid,max(leaguescore)as MaxLeagueScore from(\r\n" + 
					"select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R group by leaguename,leaguecommissionerid) as R3 on (R3.MaxLeagueScore=R2.LeagueScore and R2.leaguename=R3.leaguename and R2.leaguecommissionerid=R3.leaguecommissionerid); \r\n" + 
					"");
			
			while (myRs.next()) {
				LeagueWinner tempLeague = convertRowToLeague(myRs);
				list.add(tempLeague);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	private LeagueWinner convertRowToLeague(ResultSet myRs) throws SQLException {
		
		String teamName = myRs.getString("teamname");
		String leagueName = myRs.getString("leaguename");
		int leagueScore = myRs.getInt("leaguescore");
		
		LeagueWinner tempLeague = new LeagueWinner(teamName,leagueName,leagueScore);
		
		return tempLeague;
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
		
		LeagueWinnerDAO dao = new LeagueWinnerDAO();
		
		System.out.println(dao.getAllPlayers());
	}
}