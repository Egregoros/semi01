package vo;

public class CafeNavBoardVo {
	int boardNum;
	String boardName;
	
	public CafeNavBoardVo() {}
	
	public CafeNavBoardVo(int boardNum, String boardName) {
		super();
		this.boardNum = boardNum;
		this.boardName = boardName;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
}
