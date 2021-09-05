package TableEntities;
import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		
		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FootballPremierLeague", "VaibhavSethia07", "Vaibhav@3550");
			
			Statement myStatement = myConnection.createStatement();
			
			ResultSet myResult = myStatement.executeQuery("select * from owners");
		
			while(myResult.next()) {
				System.out.println(myResult.getString("Fname")+", " + "," + myResult.getString("Minit") + myResult.getString("Lname") + ", "+ myResult.getString("Email"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
