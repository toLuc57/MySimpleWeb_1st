package org.hm.SimpleWeb.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hm.SimpleWeb.config.SecurityConfig;
import org.hm.SimpleWeb.utils.ResultOfStudentsViewDBUtils;

public class UserAccount {
		   
	private String userName;
	private String id;
	private String password;
	private List<String> roles = new ArrayList<String>();
	private Map<String, ResultOfStudentsView> mapInfoStudent;

	public UserAccount(String userName,String password, String id) {
		this.userName = userName;
		this.id = id;
		this.password = password;
		boolean isStudent = id.contains(Student.getMaPhanChu());
		if(isStudent) {
			roles.add(SecurityConfig.ROLE_STUDENT);
			mapInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent();
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

	public String getID() {
		return id;
	}
	public boolean getIsStudent(){
		return id.contains(Student.getMaPhanChu());
	}
	public void setPosition(String id) {
		boolean isStudent = id.contains(Student.getMaPhanChu());
		if(roles == null) {
			if(isStudent) {
				roles.add(SecurityConfig.ROLE_STUDENT);
				mapInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent();
			}
			else {
				roles.add(SecurityConfig.ROLE_TEACHER);
				roles.add(SecurityConfig.ROLE_STUDENT);
			}
		}
		else {
			if(isStudent && roles.contains(SecurityConfig.ROLE_TEACHER)) {
				roles.remove(SecurityConfig.ROLE_TEACHER);
				mapInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent();
			} 
			else {
				mapInfoStudent.clear();
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
	public Map<String, ResultOfStudentsView> getMapInfoStudent() {
		return mapInfoStudent;
	}
}
