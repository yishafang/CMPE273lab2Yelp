package model;

public class User {
	private int userId; // map to "userid"
	private String fName; // map to "firstname"
	private String lName; // map to "lastname"
	private String email; // map to "email"
	private String password; // map to "password"
	private String time; // map to "logintime"
		
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString(){
		return userId + "&" + fName + " " + lName + "&" + email + "&" + password + "&" + time;
	}
}
