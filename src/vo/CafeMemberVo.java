package vo;

import java.sql.Date;

public class CafeMemberVo {
	private int userNum;
	private int cafeNum;
	private String cafeMemNick;
	private int cafeMemGradeNum;
	private int cafeInviteCount;
	private Date cafeMemRegDate;
	
	public CafeMemberVo() {}

	public CafeMemberVo(int userNum, int cafeNum, String cafeMemNick, int cafeMemGradeNum, int cafeInviteCount, Date cafeMemRegDate) {
		super();
		this.userNum = userNum;
		this.cafeNum = cafeNum;
		this.cafeMemNick = cafeMemNick;
		this.cafeMemGradeNum = cafeMemGradeNum;
		this.cafeInviteCount = cafeInviteCount;
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

	public Date getCafeMemRegDate() {
		return cafeMemRegDate;
	}

	public void setCafeMemRegDate(Date cafeMemRegDate) {
		this.cafeMemRegDate = cafeMemRegDate;
	}
	
	
}
