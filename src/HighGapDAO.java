package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class HighGapDAO {

	private Connection myConn;
	
	public HighGapDAO() throws Exception {
		
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
	
	public List<HighGap> getAllHighGap() throws Exception {
		List<HighGap> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select matchid,uefateamname,(max(score)-min(score))as Team_Perfomance_Gap from playermatchscore natural join players group by matchid,uefateamname order by matchid;\r\n");
			
			while (myRs.next()) {
				HighGap tempGap = convertRowToHighGap(myRs);
				list.add(tempGap);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	private HighGap convertRowToHighGap(ResultSet myRs) throws SQLException {
		
		int matchID = myRs.getInt("matchid");
		String UEFATeam = myRs.getString("uefateamname");
		double teamPerformanceGap = myRs.getDouble("team_perfomance_gap");
		
		HighGap tempGap = new HighGap(matchID,UEFATeam,teamPerformanceGap);
		
		return tempGap;
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
		
		HighGapDAO dao = new HighGapDAO();

		System.out.println(dao.getAllHighGap());
	}
}

