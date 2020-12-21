package vo;

import java.sql.Date;

public class PostVo {
	private int postNum;
	private int cafepostnum;
	private int boardNum;
	private String postTitle;
	private String postContent; 
	private Date postDate;
	private String userName;
	private int postCatNum;
	private int postInviteCount;
	
	public PostVo() {}

	public PostVo(int postNum, int cafepostnum, int boardNum, String postTitle, String postContent,
			Date postDate, String userName, int postCatNum, int postInviteCount) {
		super();
		this.postNum = postNum;
		this.cafepostnum = cafepostnum;
		this.boardNum = boardNum;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postDate = postDate;
		this.userName = userName;
		this.postCatNum = postCatNum;
		this.postInviteCount = postInviteCount;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getCafepostnum() {
		return cafepostnum;
	}

	public void setCafepostnum(int cafepostnum) {
		this.cafepostnum = cafepostnum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPostCatNum() {
		return postCatNum;
	}

	public void setPostCatNum(int postCatNum) {
		this.postCatNum = postCatNum;
	}

	public int getPostInviteCount() {
		return postInviteCount;
	}

	public void setPostInviteCount(int postInviteCount) {
		this.postInviteCount = postInviteCount;
	}

	
}
