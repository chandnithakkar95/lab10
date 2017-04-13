package Bean;

public class Category {
	private int cate_id;
	private String category;
	public Category(int cate_id,String cate) {
		// TODO Auto-generated constructor stub
		setCategory(cate);
		setCatId(cate_id);
	}
	public int getCatId() {
		return cate_id;
	}
	public void setCatId(int cate_id) {
		this.cate_id = cate_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
