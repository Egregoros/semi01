package vo;

public class MemberCafeListVo {
	private String cafeName;
	private String cafeMemGradeName;
	private String nickName;
	private String cafeMemRegDate;
	private int cafeNum;
	
	public MemberCafeListVo() {}

	public MemberCafeListVo(String cafeName, String cafeMemGradeName, String nickName, String cafeMemRegDate, int cafeNum) {
		super();
		this.cafeName = cafeName;
		this.cafeMemGradeName = cafeMemGradeName;
		this.nickName = nickName;
		this.cafeMemRegDate = cafeMemRegDate;
		this.cafeNum = cafeNum;
	}

	public String getCafeName() {
		return cafeName;
	}

	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}

	public String getCafeMemGradeName() {
		return cafeMemGradeName;
	}

	public void setCafeMemGradeName(String cafeMemGradeName) {
		this.cafeMemGradeName = cafeMemGradeName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCafeMemRegDate() {
		return cafeMemRegDate;
	}

	public void setCafeMemRegDate(String cafeMemRegDate) {
		this.cafeMemRegDate = cafeMemRegDate;
	}
	
	public int getCafeNum() {
		return cafeNum;
	}
	
	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}
	
	
}
