package vo;

import java.sql.Date;

public class UserInfoVo {
	private int userNum;
	private String id;
	private String pwd;
	private String nickName;
	private String name;
	private String addr;
	private String email;
	private Date birth;
	private String phone;
	private int isLive;
	
	public UserInfoVo() {}

	public UserInfoVo(int userNum, String id, String pwd, String nickName, String name, String addr, String email,
			Date birth, String phone, int isLive) {
		super();
		this.userNum = userNum;
		this.id = id;
		this.pwd = pwd;
		this.nickName = nickName;
		this.name = name;
		this.addr = addr;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
		this.isLive = isLive;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsLive() {
		return isLive;
	}

	public void setIsLive(int isLive) {
		this.isLive = isLive;
	}
	
}
