package vo;

public class CafeBoardCatVo {
	private int boardCatNum;
	private int cafeNum;
	private String catName;
	private int catOrder;
	
	public CafeBoardCatVo() {}

	public CafeBoardCatVo(int boardCatNum, int cafeNum, String catName, int catOrder) {
		super();
		this.boardCatNum = boardCatNum;
		this.cafeNum = cafeNum;
		this.catName = catName;
		this.catOrder = catOrder;
	}

	public int getBoardCatNum() {
		return boardCatNum;
	}

	public void setBoardCatNum(int boardCatNum) {
		this.boardCatNum = boardCatNum;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getCatOrder() {
		return catOrder;
	}

	public void setCatOrder(int catOrder) {
		this.catOrder = catOrder;
	}
	
}
