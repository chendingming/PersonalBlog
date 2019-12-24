package bean;

public class UserBean {
	private int id;
	private String name;
	private String blog_adress;
	private String email_address;
	private String password;
	private int type;
	private int stranger;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlog_adress() {
		return blog_adress;
	}
	public void setBlog_adress(String blog_adress) {
		this.blog_adress = blog_adress;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStranger() {
		return stranger;
	}
	public void setStranger(int stranger) {
		this.stranger = stranger;
	}
}
