package vo;


public class PostListVo {
	private int postNum;
	private int cafePostNum;
	private String postTitle;
	private String postwriter;
	private String postDate;
	private int postInviteCount;
	
	public PostListVo() {}

	public PostListVo(int postNum, int cafePostNum, String postTitle, String postwriter, String postDate,
			int postInviteCount) {
		super();
		this.postNum = postNum;
		this.cafePostNum = cafePostNum;
		this.postTitle = postTitle;
		this.postwriter = postwriter;
		this.postDate = postDate;
		this.postInviteCount = postInviteCount;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getCafePostNum() {
		return cafePostNum;
	}

	public void setCafePostNum(int cafePostNum) {
		this.cafePostNum = cafePostNum;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostWriter() {
		return postwriter;
	}

	public void setPostWriter(String postwriter) {
		this.postwriter = postwriter;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public int getPostInviteCount() {
		return postInviteCount;
	}

	public void setPostInviteCount(int postInviteCount) {
		this.postInviteCount = postInviteCount;
	}

}
