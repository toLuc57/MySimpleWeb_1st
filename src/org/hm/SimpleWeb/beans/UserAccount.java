package org.hm.SimpleWeb.beans;

import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.config.SecurityConfig;
import org.hm.SimpleWeb.utils.ResultOfStudentsViewDBUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;

public class UserAccount {
		   
	private String userName;
	private String id;
	private String password;
	private List<String> roles = new ArrayList<String>();
	private List<ResultOfStudentsView> listInfoStudent;

	public UserAccount(String userName,String password, String id) {
		this.userName = userName;
		this.id = id;
		this.password = password;
		boolean isStudent = id.contains(StudentDBUtils.getMaKhoa8()) || 
				id.contains(StudentDBUtils.getMaKhoa9());
		if(isStudent) {
			roles.add(SecurityConfig.ROLE_STUDENT);
			listInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent(id);
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
		return id.contains(StudentDBUtils.getMaKhoa8()) 
				|| id.contains(StudentDBUtils.getMaKhoa9());
	}
	public void setPosition(String _id) {
		boolean isStudent = _id.contains(StudentDBUtils.getMaKhoa8()) || 
				_id.contains(StudentDBUtils.getMaKhoa9());
		if(roles == null) {
			if(isStudent) {
				roles.add(SecurityConfig.ROLE_STUDENT);
				listInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent(_id);
			}
			else {
				roles.add(SecurityConfig.ROLE_TEACHER);
				roles.add(SecurityConfig.ROLE_STUDENT);
			}
		}
		else {
			if(isStudent && roles.contains(SecurityConfig.ROLE_TEACHER)) {
				roles.remove(SecurityConfig.ROLE_TEACHER);
				listInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent(_id);
			} 
			else {
				listInfoStudent.clear();
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
	public List<ResultOfStudentsView> getListInfoStudent() {
		return listInfoStudent;
	}
}
