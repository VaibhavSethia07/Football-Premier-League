package TableEntities;
import java.sql.*;

public class Update {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306//FootballPremierLeague";
		String user = "VaibhavSethia07";
		String password = "Vaibhav@3550";
		
		try {

			
			Connection myConnection = DriverManager.getConnection(url, user, password);
			
			Statement myStatement = myConnection.createStatement();
			
			String sql = "";
			
			myStatement.executeUpdate(sql);
			
			System.out.print("Update successfull");
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
}
