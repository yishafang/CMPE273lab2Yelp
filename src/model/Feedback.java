package model;

public class Feedback {
	private int fId; // map to "feedbackid"
	private int uId; // map to "uid"
	private int eId; // map to "eid"
	private float rating; // map to "rating"
	private String review; // map to "review"
	
	public int getFId() {
		return fId;
	}
	public void setFId(int fId) {
		this.fId = fId;
	}
	public int getUId() {
		return uId;
	}
	public void setUId(int uId) {
		this.uId = uId;
	}
	public int getEId() {
		return eId;
	}
	public void setEId(int eId) {
		this.eId = eId;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	@Override
	public String toString(){
		return fId + "&" + uId + "&" + eId + "&" + rating + "&" + review;
	}
}
