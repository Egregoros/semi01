package vo;

import java.sql.Date;

public class messageVo {
	private int messNum;
	private int recUserNum;
	private int sendUserNum;
	private String messTitle;
	private String messContent;
	private Date sendTime;
	private int checkRead;
	
	public messageVo() {}

	public messageVo(int messNum, int recUserNum, int sendUserNum, String messTitle, String messContent, Date sendTime,
			int checkRead) {
		super();
		this.messNum = messNum;
		this.recUserNum = recUserNum;
		this.sendUserNum = sendUserNum;
		this.messTitle = messTitle;
		this.messContent = messContent;
		this.sendTime = sendTime;
		this.checkRead = checkRead;
	}

	public int getMessNum() {
		return messNum;
	}

	public void setMessNum(int messNum) {
		this.messNum = messNum;
	}

	public int getRecUserNum() {
		return recUserNum;
	}

	public void setRecUserNum(int recUserNum) {
		this.recUserNum = recUserNum;
	}

	public int getSendUserNum() {
		return sendUserNum;
	}

	public void setSendUserNum(int sendUserNum) {
		this.sendUserNum = sendUserNum;
	}

	public String getMessTitle() {
		return messTitle;
	}

	public void setMessTitle(String messTitle) {
		this.messTitle = messTitle;
	}

	public String getMessContent() {
		return messContent;
	}

	public void setMessContent(String messContent) {
		this.messContent = messContent;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getCheckRead() {
		return checkRead;
	}

	public void setCheckRead(int checkRead) {
		this.checkRead = checkRead;
	}
	
	
}
