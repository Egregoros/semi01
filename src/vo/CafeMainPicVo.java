package vo;

public class CafeMainPicVo {
	private int cafePicNum;
	private int cafeNum;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	
	public CafeMainPicVo() {}

	public CafeMainPicVo(int cafePicNum, int cafeNum, String orgFileName, String saveFileName, long fileSize) {
		super();
		this.cafePicNum = cafePicNum;
		this.cafeNum = cafeNum;
		this.orgFileName = orgFileName;
		this.saveFileName = saveFileName;
		this.fileSize = fileSize;
	}

	public int getCafePicNum() {
		return cafePicNum;
	}

	public void setCafePicNum(int cafePicNum) {
		this.cafePicNum = cafePicNum;
	}

	public int getCafeNum() {
		return cafeNum;
	}

	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
}
