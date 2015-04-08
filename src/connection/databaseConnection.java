package connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Element;
import model.Feedback;
import model.User;

public class databaseConnection {
	
	Connection con = null;
	ResultSet rs = null;
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
	
	public User signIn(String email, String password){

		User u = null;
		try{
			String query = "select * from user where (email, password) = ('"+ email + "', '" + password + "')";
			System.out.println("query::" + query);
			rs = stmt.executeQuery(query);
			//System.out.println("rowcount: "+rowcount);
			if(rs.next()){
				u = new User();
				u.setUserId(rs.getInt("userid"));
				u.setEmail(rs.getString("email"));
				u.setFName(rs.getString("firstname"));
				u.setLName(rs.getString("lastname"));
				u.setTime(rs.getString("logintime"));
				System.out.println(rs.getString("email"));
				
				// insert a login time here
				String query1 = " update user set logintime = now() where email = '" + email + "'";
				System.out.println("query1::" + query1);
				stmt.executeUpdate(query1);
				
			}		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
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
	
	public String deleteC(int id) {
		String result = "";
		int rowcount;

		try {
			String query = "delete from category where categoryid =" + id;
			System.out.println("query::" + query);
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Delete category successful");
			}else{
				result="false: The data could not be deleted from the database.";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String updateC(int id, String name) {
		String result = "";
		int rowcount;
		
		try {
			String query = "update category set cname = '" + name + "' where categoryid = " + id;
			System.out.println("query::" + query);
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Update category successful");
			}else{
				result="false: The data could not be updated in the database.";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String addE(int cId, String eName, String description) {
		String result = "";
		int rowcount;
		try {
			String query = "insert into element (cid, ename, description) values (" + cId + ", '" + eName + "', '" + description +"')";
			System.out.println("query::" + query);
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Add element successful");
			}
			else{
				result="false: The element could not be added into the database.";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String addReview(int uId, int eId, float rate, String review) {
		String result = "";
		int rowcount;
		try {
			String query = "insert into feedback (uid, eid, rating, review) values (" + uId + ", " + eId + ", " + rate + ", '" + review +"')";
			System.out.println("query::" + query);
			rowcount = stmt.executeUpdate(query);
			if(rowcount > 0) {
				result = "true";
				System.out.println("Post review successful!");
			}else {
				result = "false: the review could not be added into database";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Category[] fetchCategory(String sql){
		List<Category> al = new ArrayList<Category>();
		ResultSet rs = null;
		try {
			System.out.println("query::" + sql);
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Category cat = new Category();
				int id = rs.getInt(1);
				System.out.println("id:" + id);
				cat.setCategoryId(id);
				String name = rs.getString(2);
				System.out.println("name:" + name);
				cat.setCategoryName(name);
				al.add(cat);
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }
//		finally{
//        	
//        	try {
//        		if( rs != null)
//            		rs.close();
//            	if( stmt != null)
//            		stmt.close();
//            	if( con != null)
//            		con.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//        }
		
		Category[] result = new Category[al.size()];
		for(int i = 0;i < al.size(); i++){
			  result[i] = al.get(i);
			  System.out.println("result is:" + result[i].toString());
		}
		return result;
	}
	
	public User[] fetchUser(String sql) {
		List<User> al = new ArrayList<User>();
		ResultSet rs = null;
		try {
			System.out.println("query::" + sql);
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				User user = new User();
				int id = rs.getInt(1);
				System.out.println("id:" + id);
				user.setUserId(id);
				String fName = rs.getString(2);
				System.out.println("fName:" + fName);
				user.setFName(fName);
				String lName = rs.getString(3);
				System.out.println("lName:" + lName);
				user.setLName(lName);
				String email = rs.getString(4);
				System.out.println("email:" + email);
				user.setEmail(email);
				String password = rs.getString(5);
				System.out.println("password:" + password);
				user.setPassword(password);
				String time = rs.getString(6);
				System.out.println("time:" + time);
				user.setTime(time);
				al.add(user);
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }finally{
        	
        	try {
        		if( rs != null)
            		rs.close();
            	if( stmt != null)
            		stmt.close();
            	if( con != null)
            		con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
        }
		
		User[] result = new User[al.size()];
		for(int i = 0;i < al.size(); i++){
			  result[i] = al.get(i);
			  System.out.println("result is:" + result[i].toString());
		}
		return result;
	}
	
	
	public Feedback[] getFeedbacks(int eid){
		String sql = "select * from feedback where eid="+eid;
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		try {
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				Feedback f = new Feedback();
				f.setEId(rs.getInt("eid"));
				f.setFId(rs.getInt("feedbackid"));
				f.setRating(rs.getFloat("rating"));
				f.setReview(rs.getString("review"));
				f.setUId(rs.getInt("uid"));
				feedbacks.add(f);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Feedback[] fs= new Feedback[feedbacks.size()];
		for(int i = 0; i < feedbacks.size(); i++){
			fs[i] = feedbacks.get(i);
		}
		return fs;
	}
	
	public Element[] elementByUserId(String sql) {
	
		List<Element> al = new ArrayList<Element>();
		ResultSet rs1 = null;
		Statement stmt1 = null;
		try {
			System.out.println("query::" + sql);
			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery(sql);
			while(rs1.next()) {
				Element element = new Element();
				int eId = rs1.getInt(1);
				System.out.println("eId:" + eId);
				element.setEId(eId);
				int cId = rs1.getInt(2);
				System.out.println("cId:" + cId);
				element.setCId(cId);
				String eName = rs1.getString(3);
				System.out.println("eName:" + eName);
				element.setEName(eName);
				String description = rs1.getString(4);
				System.out.println("description:" + description);
				element.setDescription(description);
				Feedback[] feedbacks  = getFeedbacks(eId);
				element.setFeedbacks(feedbacks);
				al.add(element);
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }finally{
        	
//        	try {
//        		if( rs != null)
//            		rs.close();
//            	if( stmt != null)
//            		stmt.close();
//            	if( con != null)
//            		con.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
        }
		
		Element[] result = new Element[al.size()];
		for(int i = 0;i < al.size(); i++){
			  result[i] = al.get(i);
			  System.out.println("result is:" + result[i].toString());
		}
		return result;
	}
	
	public Element[] fetchElement(String sql) {
		List<Element> al = new ArrayList<Element>();
		ResultSet rs = null;
		try {
			System.out.println("query::" + sql);
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Element element = new Element();
				int eId = rs.getInt(1);
				System.out.println("eId:" + eId);
				element.setEId(eId);
				int cId = rs.getInt(2);
				System.out.println("cId:" + cId);
				element.setCId(cId);
				String eName = rs.getString(3);
				System.out.println("eName:" + eName);
				element.setEName(eName);
				String description = rs.getString(4);
				System.out.println("description:" + description);
				element.setDescription(description);
				
				al.add(element);
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }finally{
        	
//        	try {
//        		if( rs != null)
//            		rs.close();
//            	if( stmt != null)
//            		stmt.close();
//            	if( con != null)
//            		con.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
        }
		
		Element[] result = new Element[al.size()];
		for(int i = 0;i < al.size(); i++){
			  result[i] = al.get(i);
			  System.out.println("result is:" + result[i].toString());
		}
		return result;
	}
	
	public Feedback[] fetchFeedback(String sql) {
		List<Feedback> al = new ArrayList<Feedback>();
		ResultSet rs = null;
		try {
			System.out.println("query::" + sql);
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Feedback feedback = new Feedback();
				int fId = rs.getInt(1);
				System.out.println("fId:" + fId);
				feedback.setFId(fId);
				int uId = rs.getInt(2);
				System.out.println("uId:" + uId);
				feedback.setUId(uId);
				int eId = rs.getInt(3);
				System.out.println("eId:" + eId);
				feedback.setEId(eId);
				float rating = rs.getFloat(4);
				System.out.println("rating:" + rating);
				feedback.setRating(rating);
				String review = rs.getString(5);
				System.out.println("review:" + review);
				feedback.setReview(review);
				
				al.add(feedback);
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }finally{
        	
//        	try {
//        		if( rs != null)
//            		rs.close();
//            	if( stmt != null)
//            		stmt.close();
//            	if( con != null)
//            		con.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
        }
		
		Feedback[] result = new Feedback[al.size()];
		for(int i = 0;i < al.size(); i++){
			  result[i] = al.get(i);
			  System.out.println("result is:" + result[i].toString());
		}
		return result;
	}

}
