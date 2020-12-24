package vo;

public class CafeMemGradeVo {
	private int cafeNum;
	private int cafeMemGradeNum;
	private String cafeMemGradeName;
	
	public CafeMemGradeVo() {}

	public CafeMemGradeVo(int cafeNum, int cafeMemGradeNum, String cafeMemGradeName) {
		super();
		this.cafeNum = cafeNum;
		this.cafeMemGradeNum = cafeMemGradeNum;
		this.cafeMemGradeName = cafeMemGradeName;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public int getCafeMemGradeNum() {
		return cafeMemGradeNum;
	}

	public void setCafeMemGradeNum(int cafeMemGradeNum) {
		this.cafeMemGradeNum = cafeMemGradeNum;
	}

	public String getCafeMemGradeName() {
		return cafeMemGradeName;
	}

	public void setCafeMemGradeName(String cafeMemGradeName) {
		this.cafeMemGradeName = cafeMemGradeName;
	}
	
}
