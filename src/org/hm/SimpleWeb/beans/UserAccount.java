package org.hm.SimpleWeb.beans;

import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.config.SecurityConfig;

public class UserAccount {
		   
	private String userName;
	private boolean student;
	private String password;
	private List<String> roles = new ArrayList<String>();;

	public UserAccount(String userName,String password, boolean student) {
		this.userName = userName;
		this.student = student;
		this.password = password;
		if(student) {
			roles.add(SecurityConfig.ROLE_STUDENT);
		}
		else {
			roles.add(SecurityConfig.ROLE_TEACHER);
			roles.add(SecurityConfig.ROLE_STUDENT);
		}
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
		if(roles == null) {
			if(student) {
				roles.add(SecurityConfig.ROLE_STUDENT);
			}
			else {
				roles.add(SecurityConfig.ROLE_TEACHER);
				roles.add(SecurityConfig.ROLE_STUDENT);
			}
		}
		else {
			if(student && roles.contains(SecurityConfig.ROLE_TEACHER)) {
				roles.remove(SecurityConfig.ROLE_TEACHER);
			}
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles(){
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
