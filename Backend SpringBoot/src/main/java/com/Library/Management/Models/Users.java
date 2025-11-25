package com.Library.Management.Models;

public class Users {
	private int userId;
	private String userName;
	private String email;
	private String name;
	private String password;
	private int roleId;

	public Users() {
	}

	public Users(int userId, String userName, String email, String name, String password, int roleId) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.name = name;
		this.password = password;
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwordHash) {
		this.password = passwordHash;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", name=" + name + ", roleId="
				+ roleId + "]";
	}
}
