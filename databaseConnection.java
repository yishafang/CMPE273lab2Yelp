package connection;

import java.sql.*;

public class databaseConnection {
	
	Connection con = null;
	static ResultSet rs;
    Statement stmt = null;
	
	databaseConnection(){		
		try {			
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab2yelp","root","");
				stmt = con.createStatement();
				if(!con.isClosed())
					System.out.println("Successfully Connected!!!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public String signUp(String firstName, String lastName, String email, String password){
		String result = "";
		int rowcount;
		try {
			String query = "Insert into user (firstname, lastname, email, password) values ('" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "')";
			System.out.println("query::" + query);
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Insert Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String signIn(String email, String password){
		String result = "";
		try{
			String query = "select * from user where (email, password) = ('"+ email + "', '" + password + "')";
			System.out.println("query::" + query);
			rs = stmt.executeQuery(query);
			//System.out.println("rowcount: "+rowcount);
			if(rs.next()){
				result="true";
				System.out.println(rs.getString("email"));
			}
			else{
				result="false: Oops, something wrong with your email or password...";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String createC(String name) {
		String result = "";
		int rowcount;
		try {
			String query = "insert into category (cname) values ('" + name + "')";
			System.out.println("query::" + query);
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Insert category successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
/*	
	public ArrayList<String> fetchData(String sql){
		ArrayList<String> result = new ArrayList<String>();
		try {
			System.out.println("query::" + sql);
			ResultSet rs = stmt.executeQuery(sql);
//			int length = 0;
//			if (rs.last()) {
//			  length = rs.getRow();
//			  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
//			}
			while(rs.next()) {
				String s = rs.getString(1);
				System.out.print("rs:" + rs);
				System.out.print("s:" + s);
				result.add(s);			
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}*/

}
