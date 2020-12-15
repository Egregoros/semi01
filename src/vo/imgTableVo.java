package vo;

public class imgTableVo {
	private int imgNum;
	private int postNum;
	private String saveImgName;
	private String orgImgName;
	
	public imgTableVo() {}

	public imgTableVo(int imgNum, int postNum, String saveImgName, String orgImgName) {
		super();
		this.imgNum = imgNum;
		this.postNum = postNum;
		this.saveImgName = saveImgName;
		this.orgImgName = orgImgName;
	}

	public int getImgNum() {
		return imgNum;
	}

	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getSaveImgName() {
		return saveImgName;
	}

	public void setSaveImgName(String saveImgName) {
		this.saveImgName = saveImgName;
	}

	public String getOrgImgName() {
		return orgImgName;
	}

	public void setOrgImgName(String orgImgName) {
		this.orgImgName = orgImgName;
	}
	
	
}
