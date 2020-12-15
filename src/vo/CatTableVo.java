package vo;

public class CatTableVo {
	private int catNum;
	private String catName;
	
	public CatTableVo() {}

	public CatTableVo(int catNum, String catName) {
		super();
		this.catNum = catNum;
		this.catName = catName;
	}

	public int getCatNum() {
		return catNum;
	}

	public void setCatNum(int catNum) {
		this.catNum = catNum;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	
}
