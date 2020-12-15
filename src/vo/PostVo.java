package vo;

import java.sql.Date;

public class PostVo {
	private int postNum;
	private int boardNum;
	private int cafeNum;
	private String postTitle;
	private String postContent;
	private Date postDate;
	private int userNum;
	private int postCatNum;
	
	public PostVo() {}

	public PostVo(int postNum, int boardNum, int cafeNum, String postTitle, String postContent, Date postDate,
			int userNum, int postCatNum) {
		super();
		this.postNum = postNum;
		this.boardNum = boardNum;
		this.cafeNum = cafeNum;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postDate = postDate;
		this.userNum = userNum;
		this.postCatNum = postCatNum;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
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

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getPostCatNum() {
		return postCatNum;
	}

	public void setPostCatNum(int postCatNum) {
		this.postCatNum = postCatNum;
	}
	
	
}
