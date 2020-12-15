package vo;

public class cafeMemberVo {
	private int userNum;
	private int cafeNum;
	private String cafeMemNick;
	private int cafeMemGrade;
	
	public cafeMemberVo() {}

	public cafeMemberVo(int userNum, int cafeNum, String cafeMemNick, int cafeMemGrade) {
		super();
		this.userNum = userNum;
		this.cafeNum = cafeNum;
		this.cafeMemNick = cafeMemNick;
		this.cafeMemGrade = cafeMemGrade;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public String getCafeMemNick() {
		return cafeMemNick;
	}

	public void setCafeMemNick(String cafeMemNick) {
		this.cafeMemNick = cafeMemNick;
	}

	public int getCafeMemGrade() {
		return cafeMemGrade;
	}

	public void setCafeMemGrade(int cafeMemGrade) {
		this.cafeMemGrade = cafeMemGrade;
	}
	
	
}
