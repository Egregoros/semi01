package vo;

public class cafeGradeVo {
	private int gradeNum;
	private String gradeName;
	
	public cafeGradeVo() {}

	public cafeGradeVo(int gradeNum, String gradeName) {
		super();
		this.gradeNum = gradeNum;
		this.gradeName = gradeName;
	}

	public int getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
}
