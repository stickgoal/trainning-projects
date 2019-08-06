package test.orm.test.entity;

import java.util.Date;

import com.woniuxy.rocket.orm.annotation.Column;
import com.woniuxy.rocket.orm.annotation.Entity;
import com.woniuxy.rocket.orm.annotation.Id;

@Entity(tableName = "test_user")
public class User {
	@Column(columnName = "user_id")
	@Id
	private int userId;
	
	@Column(columnName = "username")
	private String username;
	
	@Column(columnName = "password")
	private String password;
	
	@Column(columnName = "reg_date")
	private Date registerTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", registerTime="
				+ registerTime + "]";
	}
	
	
}
