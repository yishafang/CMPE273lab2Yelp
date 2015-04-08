package model;

public class Category {
	private int categoryId; //map to "categoryid"
	private String categoryName; // map to "cname"
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString(){
		return categoryId + "&" + categoryName;
	}
}
