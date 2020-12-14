package VO;

public class cafeBoardCatVo {
	private int boardCatNum;
	private int cafeNum;
	private String catName;
	private int carOrder;
	
	public cafeBoardCatVo() {}

	public cafeBoardCatVo(int boardCatNum, int cafeNum, String catName, int carOrder) {
		super();
		this.boardCatNum = boardCatNum;
		this.cafeNum = cafeNum;
		this.catName = catName;
		this.carOrder = carOrder;
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

	public int getCarOrder() {
		return carOrder;
	}

	public void setCarOrder(int carOrder) {
		this.carOrder = carOrder;
	}
	
}
