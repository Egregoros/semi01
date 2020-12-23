package vo;

public class CafeBoardVo {
	private int boardNum;
	private int cafeBoardNum;
	private int boardCatNum;
	private String boardName;
	private int useGrade;
	private int orderNum;
	
	public CafeBoardVo() {}

	public CafeBoardVo(int boardNum, int cafeBoardNum, int boardCatNum, String boardName, int useGrade, int orderNum) {
		super();
		this.boardNum = boardNum;
		this.cafeBoardNum = cafeBoardNum;
		this.boardCatNum = boardCatNum;
		this.boardName = boardName;
		this.useGrade = useGrade;
		this.orderNum = orderNum;
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

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	
}
