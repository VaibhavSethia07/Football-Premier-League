package TableEntities;
import java.sql.*;

public class Insert {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/FootballPremierLeague";
		String user = "VaibhavSethia07";
		String password = "Vaibhav@3550";
		
		try {
			Connection myConnection = DriverManager.getConnection(url, user, password);
			
			Statement myStatement = myConnection.createStatement();
			
			String sql = "insert into owners (ownerid,fname,minit,lname,email)\r\n" + 
					"values (1,'Harold','D','Hunt','harokldhunt@gmail.com');";
			
			myStatement.executeUpdate(sql);
			
			System.out.println("Insert successfull.");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
