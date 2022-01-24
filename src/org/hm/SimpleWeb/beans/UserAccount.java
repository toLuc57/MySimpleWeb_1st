package org.hm.SimpleWeb.beans;

public class UserAccount {
		   
	private String userName;
	private boolean student;
	private String password;
   
	public UserAccount() {
       
	}
   
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isStudent() {
		return student;
	}

	public void setPosition(boolean student) {
		this.student = student;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
