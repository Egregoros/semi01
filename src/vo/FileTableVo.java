package vo;

public class fileTableVo {
	private int fileNum;
	private int postNum;
	private String saveFileName;
	private String orgFileName;
	
	public fileTableVo() {}

	public fileTableVo(int fileNum, int postNum, String saveFileName, String orgFileName) {
		super();
		this.fileNum = fileNum;
		this.postNum = postNum;
		this.saveFileName = saveFileName;
		this.orgFileName = orgFileName;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	
	
}
