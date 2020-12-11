package userinfo.vo;

public class userinfo1 {
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String addr;
	private String email;
	private String birth;
	private String phone;
	public userinfo1(String id, String pwd, String name, String nickname, String addr, String email, String birth,
			String phone) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.addr = addr;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
