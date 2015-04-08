package connection;

import javax.jws.WebService;

import model.Category;
import model.Element;
import model.Feedback;
import model.User;


@WebService
public class Service {	
	//databaseConnection db=new databaseConnection();
	
	ConnectionPooling db = new ConnectionPooling();
	
	public String signUp(String firstname, String lastname, String email, String password)
	{
		System.out.println("Inside Signup");
		String result;
		
		/**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*/
		
		result = db.signUp(firstname, lastname, email, password);
		
		return result;//this value is returned to the calling servlet
	}	
	
	public User signIn(String email, String password) {
		System.out.println("Inside signIn");
		User u  = db.signIn(email, password);
		return u;
	}
	
	public String createC(String name) {
		System.out.println("Inside createC");
		String result;
		result = db.createC(name);
		return result;
	}
	
	public String deleteC(int id) {
		System.out.println("Inside deleteC");
		String result;
		result = db.deleteC(id);
		return result;
	}
	
	public String updateC(int id, String name) {
		System.out.println("Inside updateC");
		String result;
		result = db.updateC(id, name);
		return result;
	}
	
	public String addE(int cId, String eName, String description) {
		System.out.println("Inside addE");
		String result;
		result = db.addE(cId, eName, description);
		return result;
	}
	
	public String addReview(int uId, int eId, float rate, String review) {
		System.out.println("Inside add review");
		String result;
		result = db.addReview(uId, eId, rate, review);
		return result;
	}
	
	public Category[] fetchCategory(String sql) {
		System.out.println("Inside fetchCategory");
		Category[] result;
		result = db.fetchCategory(sql);
		return result;
	}
	
	public User[] fetchUser(String sql) {
		System.out.println("Inside fetchUser");
		User[] result;
		result = db.fetchUser(sql);
		return result;
	}
	
	public Element[] fetchElement(String sql) {
		System.out.println("Inside fetchElement");
		Element[] result;
		result = db.fetchElement(sql);
		return result;
	}
	
	public Feedback[] fetchFeedback(String sql) {
		System.out.println("Inside fetchFeedback");
		Feedback[] result;
		result = db.fetchFeedback(sql);
		return result;
	}
	
	public Element[] getElmtByUID(String sql){
		Element[] result;
		result = db.elementByUserId(sql);
		return result;

	}
}