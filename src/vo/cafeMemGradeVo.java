package VO;

import java.sql.Date;

public class cafeMemGradeVo {
	private int cafeNum;
	private int cafeMemGrade;
	private String cafeMemGradeName;
	private Date userRegDate;
	
	public cafeMemGradeVo() {}

	public cafeMemGradeVo(int cafeNum, int cafeMemGrade, String cafeMemGradeName, Date userRegDate) {
		super();
		this.cafeNum = cafeNum;
		this.cafeMemGrade = cafeMemGrade;
		this.cafeMemGradeName = cafeMemGradeName;
		this.userRegDate = userRegDate;
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

	public Date getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(Date userRegDate) {
		this.userRegDate = userRegDate;
	}
	
	
}
