package TableEntities;

import java.util.*;
import java.sql.*;
import java.io.*;

public class OwnersTopScorerDAO {

	private Connection myConn;
	
	public OwnersTopScorerDAO() throws Exception {
		
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
	
	public List<Owner> getAllOwners() throws Exception {
		List<Owner> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from owners\r\n" + 
					"natural join\r\n" + 
					"\r\n" + 
					"(select distinct ownerid from teamplayers\r\n" + 
					"natural join\r\n" + 
					"(select playerid from\r\n" + 
					"\r\n" + 
					"(select playerid,avg(steals)as Avg_steals from playermatchperformance group by playerid) as R1\r\n" + 
					" \r\n" + 
					"join\r\n" + 
					"\r\n" + 
					"(select max(Avg_steals)as max_steals from\r\n" + 
					"(select playerid,avg(steals)as Avg_steals from playermatchperformance group by playerid)as R1) as R2\r\n" + 
					"on (R1.avg_steals=R2.max_steals))as Rin) as Rinin;");
			
			while (myRs.next()) {
				Owner tempOwner = convertRowToOwner(myRs);
				list.add(tempOwner);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	private Owner convertRowToOwner(ResultSet myRs) throws SQLException {
		
		int ownerID = myRs.getInt("ownerid");
		String fName = myRs.getString("fname");
		String mInit = myRs.getString("minit");
		String UEFATeam = myRs.getString("lname");
		String email = myRs.getString("email");
		Owner tempOwner = new Owner(ownerID,fName,'A',UEFATeam,email);
		
		return tempOwner;
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
		
		OwnersTopScorerDAO dao = new OwnersTopScorerDAO();
		
		System.out.println(dao.getAllOwners());
	}
}

