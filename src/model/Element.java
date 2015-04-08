package model;

import java.util.ArrayList;

public class Element {
	private int eId; // map to "elementid"
	private int cId; // map to "cid"
	private String eName; // map to "ename"
	private String description; // map to "description"
	private Feedback[] feedbacks;
	
	
	public Feedback[] getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(Feedback[] feedbacks) {
		this.feedbacks = feedbacks;
	}
	public int getEId() {
		return eId;
	}
	public void setEId(int eId) {
		this.eId = eId;
	}
	public int getCId() {
		return cId;
	}
	public void setCId(int cId) {
		this.cId = cId;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String eName) {
		this.eName = eName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return eId + "&" + cId + "&" + eName + "&" + description;
	}	
	
}
