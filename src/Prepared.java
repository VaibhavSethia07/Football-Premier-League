package TableEntities;
import java.sql.*;
import java.sql.DriverManager;

public class Prepared {

	public static void main(String[] args) {
		
		Connection myConnection = null;
		PreparedStatement myStatement = null;
		ResultSet myResult = null;
		
		try {
			
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FootballPremierLeague", "VaibhavSethia07", "Vaibhav@3550");
			
			myStatement = myConnection.prepareStatement("select * from employees where salary > ? and department=?");
			
			myStatement.setDouble(1, 8000);
			myStatement.setString(2, "Legal");
			
			myResult = myStatement.executeQuery();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
