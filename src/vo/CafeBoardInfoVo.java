package vo;

public class CafeBoardInfoVo {
	private int boardNum;
	private int cafeBoardNum;
	private int boardCatNum;
	private String boardName;
	private int useGrade;
	private int orderNum;
	private int postCount;
	
	public CafeBoardInfoVo() {}

	public CafeBoardInfoVo(int boardNum, int cafeBoardNum, int boardCatNum, String boardName, int useGrade,
			int orderNum, int postCount) {
		super();
		this.boardNum = boardNum;
		this.cafeBoardNum = cafeBoardNum;
		this.boardCatNum = boardCatNum;
		this.boardName = boardName;
		this.useGrade = useGrade;
		this.orderNum = orderNum;
		this.postCount = postCount;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getCafeBoardNum() {
		return cafeBoardNum;
	}

	public void setCafeBoardNum(int cafeBoardNum) {
		this.cafeBoardNum = cafeBoardNum;
	}

	public int getBoardCatNum() {
		return boardCatNum;
	}

	public void setBoardCatNum(int boardCatNum) {
		this.boardCatNum = boardCatNum;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getUseGrade() {
		return useGrade;
	}

	public void setUseGrade(int useGrade) {
		this.useGrade = useGrade;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

}
