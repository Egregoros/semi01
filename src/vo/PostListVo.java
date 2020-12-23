package vo;


public class PostListVo {
	private int postNum;
	private int cafePostNum;
	private String postTitle;
	private String postWriter;
	private int postWriterNum;
	private String postDate;
	private int postInviteCount;
	
	public PostListVo() {}

	public PostListVo(int postNum, int cafePostNum, String postTitle, String postWriter, int postWriterNum,
			String postDate, int postInviteCount) {
		super();
		this.postNum = postNum;
		this.cafePostNum = cafePostNum;
		this.postTitle = postTitle;
		this.postWriter = postWriter;
		this.postWriterNum = postWriterNum;
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
		return postWriter;
	}

	public void setPostWriter(String postWriter) {
		this.postWriter = postWriter;
	}

	public int getPostWriterNum() {
		return postWriterNum;
	}

	public void setPostWriterNum(int postWriterNum) {
		this.postWriterNum = postWriterNum;
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
