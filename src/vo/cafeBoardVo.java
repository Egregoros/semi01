package vo;

public class cafeBoardVo {
	private int boardNum;
	private int cafeNum;
	private int boardCatNum;
	private String boardName;
	private int orderNum;
	
	public cafeBoardVo() {}

	public cafeBoardVo(int boardNum, int cafeNum, int boardCatNum, String boardName, int orderNum) {
		super();
		this.boardNum = boardNum;
		this.cafeNum = cafeNum;
		this.boardCatNum = boardCatNum;
		this.boardName = boardName;
		this.orderNum = orderNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
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
