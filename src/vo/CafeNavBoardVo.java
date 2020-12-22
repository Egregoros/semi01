package vo;

public class CafeNavBoardVo {
	int boardNum;
	int cafeBoardNum;
	String boardName;
	
	public CafeNavBoardVo() {}

	public CafeNavBoardVo(int boardNum, int cafeBoardNum, String boardName) {
		super();
		this.boardNum = boardNum;
		this.cafeBoardNum = cafeBoardNum;
		this.boardName = boardName;
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

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	
}
