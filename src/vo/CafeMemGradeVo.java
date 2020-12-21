package vo;

import java.sql.Date;

public class CafeMemGradeVo {
	private int cafeNum;
	private int cafeMemGrade;
	private String cafeMemGradeName;
	
	public CafeMemGradeVo() {}

	public CafeMemGradeVo(int cafeNum, int cafeMemGrade, String cafeMemGradeName) {
		super();
		this.cafeNum = cafeNum;
		this.cafeMemGrade = cafeMemGrade;
		this.cafeMemGradeName = cafeMemGradeName;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public int getCafeMemGrade() {
		return cafeMemGrade;
	}

	public void setCafeMemGrade(int cafeMemGrade) {
		this.cafeMemGrade = cafeMemGrade;
	}

	public String getCafeMemGradeName() {
		return cafeMemGradeName;
	}

	public void setCafeMemGradeName(String cafeMemGradeName) {
		this.cafeMemGradeName = cafeMemGradeName;
	}
	
}
