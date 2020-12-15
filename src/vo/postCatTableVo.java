package vo;

public class postCatTableVo {
	private int postCatNum;
	private int cafeNum;
	private String postCatName;
	
	public postCatTableVo() {}

	public postCatTableVo(int postCatNum, int cafeNum, String postCatName) {
		super();
		this.postCatNum = postCatNum;
		this.cafeNum = cafeNum;
		this.postCatName = postCatName;
	}

	public int getPostCatNum() {
		return postCatNum;
	}

	public void setPostCatNum(int postCatNum) {
		this.postCatNum = postCatNum;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public String getPostCatName() {
		return postCatName;
	}

	public void setPostCatName(String postCatName) {
		this.postCatName = postCatName;
	}
	
	
}
