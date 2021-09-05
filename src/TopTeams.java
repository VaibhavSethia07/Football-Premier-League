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

public class TopTeams {
	private String teamName;
	
	
	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	@Override
	public String toString() {
		return "TopTeams [getTeamName()=" + getTeamName() + "]";
	}


	public TopTeams(String teamName) {
		super();
		this.teamName = teamName;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class TopTeamsDAO{
private Connection myConn;
	
	public TopTeamsDAO() throws Exception {
		
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
	
	public List<String> getTeams(int matches) throws Exception {
		List<String> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.prepareStatement("select teamname from(\r\n" + 
					"select teamname,ownerid, count(distinct matchid)as no_of_matches from (select * from teamplayers R4 where R4.playerid\r\n" + 
					"in  \r\n" + 
					"(select playerid from players\r\n" + 
					"natural join(\r\n" + 
					"select playerid from (\r\n" + 
					"select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R2\r\n" + 
					"where R2.no_of_goals  \r\n" + 
					"in (select max(no_of_goals) as max_no_of_goals from (\r\n" + 
					"select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R))as R3))as R4 group by teamname,ownerid)as R4 where no_of_matches>= ?;\r\n" + 
					"");
			myStmt.setInt(1,matches);
			myRs = myStmt.executeQuery();
			
			
			while (myRs.next()) {
				String team = myRs.getString("teamname");
				list.add(team);
			}
		}
		finally {
			close(myStmt, myRs);
		}
		
		return list;
	}
	
	
	private Player convertRowToPlayer(ResultSet myRs) throws SQLException {
		int playerID = myRs.getInt("playerid");
		String name = myRs.getString("name");
		String position = myRs.getString("position");
		String UEFATeamName = myRs.getString("uefateamname");
		
		Player tempPlayer = new Player(playerID=0,position="",UEFATeamName="",name);
		
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
		
		TopTeamsDAO dao = new TopTeamsDAO();

		System.out.println(dao.getTeams(1));
	}
}
