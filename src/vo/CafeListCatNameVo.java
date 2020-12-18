package vo;

public class CafeListCatNameVo {
	private int cafeNum;
	private int gradeNum;
	private int catNum;
	private String cafeName;
	private int userNum;
	private String cafeRegDate;
	private String catName;
	
	public CafeListCatNameVo() {}

	public CafeListCatNameVo(int cafeNum, int gradeNum, int catNum, String cafeName, int userNum, String cafeRegDate,
			String catName) {
		super();
		this.cafeNum = cafeNum;
		this.gradeNum = gradeNum;
		this.catNum = catNum;
		this.cafeName = cafeName;
		this.userNum = userNum;
		this.cafeRegDate = cafeRegDate;
		this.catName = catName;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public int getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}

	public int getCatNum() {
		return catNum;
	}

	public void setCatNum(int catNum) {
		this.catNum = catNum;
	}

	public String getCafeName() {
		return cafeName;
	}

	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getCafeRegDate() {
		return cafeRegDate;
	}

	public void setCafeRegDate(String cafeRegDate) {
		this.cafeRegDate = cafeRegDate;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	
}
