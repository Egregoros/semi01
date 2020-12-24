package vo;

import java.sql.Date;

public class CafeMemberGradeNameVo {
	private int userNum;
	private int cafeNum;
	private String cafeMemNick;
	private int cafeMemGradeNum;
	private int cafeInviteCount;
	private String cafeMemGradeName;
	private String cafeMemRegDate;
	
	public CafeMemberGradeNameVo() {}

	public CafeMemberGradeNameVo(int userNum, int cafeNum, String cafeMemNick, int cafeMemGradeNum, int cafeInviteCount,
			String cafeMemGradeName, String cafeMemRegDate) {
		super();
		this.userNum = userNum;
		this.cafeNum = cafeNum;
		this.cafeMemNick = cafeMemNick;
		this.cafeMemGradeNum = cafeMemGradeNum;
		this.cafeInviteCount = cafeInviteCount;
		this.cafeMemGradeName = cafeMemGradeName;
		this.cafeMemRegDate = cafeMemRegDate;
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

	public int getCafeMemGradeNum() {
		return cafeMemGradeNum;
	}

	public void setCafeMemGradeNum(int cafeMemGradeNum) {
		this.cafeMemGradeNum = cafeMemGradeNum;
	}

	public int getCafeInviteCount() {
		return cafeInviteCount;
	}

	public void setCafeInviteCount(int cafeInviteCount) {
		this.cafeInviteCount = cafeInviteCount;
	}

	public String getCafeMemGradeName() {
		return cafeMemGradeName;
	}

	public void setCafeMemGradeName(String cafeMemGradeName) {
		this.cafeMemGradeName = cafeMemGradeName;
	}

	public String getCafeMemRegDate() {
		return cafeMemRegDate;
	}

	public void setCafeMemRegDate(String cafeMemRegDate) {
		this.cafeMemRegDate = cafeMemRegDate;
	}
	
	
}
