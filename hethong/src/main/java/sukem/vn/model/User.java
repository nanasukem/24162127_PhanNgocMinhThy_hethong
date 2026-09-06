package sukem.vn.model;

import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;
	private String userName;
	private String fullName;
	private String passWord;
	private String avatar;
	private int roleid;
	private String phone;
	private Date createdDate;

	private boolean status; // false: Chưa kích hoạt, true: Đã kích hoạt
	private String code; // Mã OTP 6 chữ số

	// 1. Constructor mặc định bắt buộc cho JPA
	public User() {
	}

	// 2. Constructor đầy đủ tham số không có ID (dùng khi Insert mới)
	public User(String email, String userName, String fullName, String passWord, String avatar, int roleid,
			String phone, Date createdDate, boolean status, String code) {
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.avatar = avatar;
		this.roleid = roleid;
		this.phone = phone;
		this.createdDate = createdDate;
		this.status = status;
		this.code = code;
	}

	// 3. Constructor 8 tham số cũ (để tương thích nếu code cũ của bạn có gọi)
	public User(String email, String userName, String fullName, String passWord, String avatar, int roleid,
			String phone, Date createdDate) {
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.avatar = avatar;
		this.roleid = roleid;
		this.phone = phone;
		this.createdDate = createdDate;
		this.status = false;
	}

	// 4. Constructor đầy đủ tất cả thuộc tính gồm cả ID
	public User(int id, String email, String userName, String fullName, String passWord, String avatar, int roleid,
			String phone, Date createdDate, boolean status, String code) {
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.avatar = avatar;
		this.roleid = roleid;
		this.phone = phone;
		this.createdDate = createdDate;
		this.status = status;
		this.code = code;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userName=" + userName + ", fullName=" + fullName
				+ ", passWord=" + passWord + ", avatar=" + avatar + ", roleid=" + roleid + ", phone=" + phone
				+ ", createdDate=" + createdDate + ", status=" + status + ", code=" + code + "]";
	}
}