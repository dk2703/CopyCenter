package com.copycenter.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAccount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userAccountID;

	private String userName;
	private String password;
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUserAccountID() {
		return userAccountID;
	}

	public void setUserAccountID(Integer userAccountID) {
		this.userAccountID = userAccountID;
	}

}
