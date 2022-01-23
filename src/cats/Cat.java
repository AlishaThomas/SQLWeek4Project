package cats;

public class Cat {
	private Integer catId;
	private String catName;
	
	
	@Override
	public String toString() {
		return "Id=" + catId + ", catName=" + catName;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
}
