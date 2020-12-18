package vo;

import java.util.ArrayList;

public class CafeNavCatVo {
	int catNum;
	String catName;
	ArrayList<CafeNavBoardVo> board;
	
	public CafeNavCatVo() {}
	
	public CafeNavCatVo(int catNum, String catName, ArrayList<CafeNavBoardVo> board) {
		this.catNum = catNum;
		this.catName = catName;
		this.board = board;
	}
	public int getCatNum() {
		return catNum;
	}
	public void setCatNum(int catNum) {
		this.catNum = catNum;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public ArrayList<CafeNavBoardVo> getBoard() {
		return board;
	}
	public void setBoard(ArrayList<CafeNavBoardVo> board) {
		this.board = board;
	}
	
	
	
}
