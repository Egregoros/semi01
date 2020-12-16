package vo;

import java.sql.Date;

public class postcommentvo {
	private int commentnum;
	private int postnum;
	private int usernum;
	private String postcomment;
	private Date commentregdate;
	
	
	public postcommentvo() {
	}
	public postcommentvo(int commentnum, int postnum, int usernum, String postcomment, Date commentregdate) {
		super();
		this.commentnum = commentnum;
		this.postnum = postnum;
		this.usernum = usernum;
		this.postcomment = postcomment;
		this.commentregdate = commentregdate;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public String getPostcomment() {
		return postcomment;
	}
	public void setPostcomment(String postcomment) {
		this.postcomment = postcomment;
	}
	public Date getCommentregdate() {
		return commentregdate;
	}
	public void setCommentregdate(Date commentregdate) {
		this.commentregdate = commentregdate;
	}
}

	
