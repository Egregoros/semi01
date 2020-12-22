package vo;

import java.sql.Date;

public class PostCommentVo {
	private int commentNum;
	private int postNum;
	private String userName;
	private int userNum;
	private String postComment;
	private Date commentRegdate;
	
	public PostCommentVo() {}
	
	public PostCommentVo(int commentNum, int postNum, String userName, int userNum, String postComment,
			Date commentRegdate) {
		super();
		this.commentNum = commentNum;
		this.postNum = postNum;
		this.userName = userName;
		this.userNum = userNum;
		this.postComment = postComment;
		this.commentRegdate = commentRegdate;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getPostComment() {
		return postComment;
	}

	public void setPostComment(String postComment) {
		this.postComment = postComment;
	}

	public Date getCommentRegdate() {
		return commentRegdate;
	}

	public void setCommentRegdate(Date commentRegdate) {
		this.commentRegdate = commentRegdate;
	}
	
}

	
