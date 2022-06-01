package org.hm.SimpleWeb.beans;

import java.util.ArrayList;
import java.util.List;

import org.hm.SimpleWeb.config.SecurityConfig;
import org.hm.SimpleWeb.utils.ResultOfStudentsViewDBUtils;
import org.hm.SimpleWeb.utils.StudentDBUtils;
import org.hm.SimpleWeb.utils.TeacherDBUtils;

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
			boolean isTeacher = id.contains(TeacherDBUtils.getTextInID());
			if (isTeacher) {
				roles.add(SecurityConfig.ROLE_TEACHER);
			}
			else {
				roles.add(SecurityConfig.ROLE_STUDENT);
				roles.add(SecurityConfig.ROLE_TEACHER);
				roles.add(SecurityConfig.ROLE_ADMIN);
			}
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
	public boolean getIsTeacher(){
		return id.contains(TeacherDBUtils.getTextInID());
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
				boolean isTeacher = id.contains(TeacherDBUtils.getTextInID());
				if (isTeacher) {
					roles.add(SecurityConfig.ROLE_TEACHER);
				}
				else {
					roles.add(SecurityConfig.ROLE_STUDENT);
					roles.add(SecurityConfig.ROLE_TEACHER);
					roles.add(SecurityConfig.ROLE_ADMIN);
				}
			}
		}
		else {
			if(isStudent) {
				if(roles.contains(SecurityConfig.ROLE_TEACHER)) {
					roles.remove(SecurityConfig.ROLE_TEACHER);
				}
				if(roles.contains(SecurityConfig.ROLE_ADMIN)) {
					roles.remove(SecurityConfig.ROLE_ADMIN);
				}
				listInfoStudent = ResultOfStudentsViewDBUtils.getMapInfoStudent(_id);
			} 
			else {
				listInfoStudent.clear();
				boolean isTeacher = id.contains(TeacherDBUtils.getTextInID());
				if (isTeacher) {
					if(roles.contains(SecurityConfig.ROLE_STUDENT)) {
						roles.remove(SecurityConfig.ROLE_STUDENT);
					}
					if(roles.contains(SecurityConfig.ROLE_ADMIN)) {
						roles.remove(SecurityConfig.ROLE_ADMIN);
					}
					if(!roles.contains(SecurityConfig.ROLE_TEACHER)) {
						roles.add(SecurityConfig.ROLE_TEACHER);
					}
				}
				else {
					if(!roles.contains(SecurityConfig.ROLE_ADMIN)) {
						roles.add(SecurityConfig.ROLE_ADMIN);
					}
					if(!roles.contains(SecurityConfig.ROLE_STUDENT)) {
						roles.add(SecurityConfig.ROLE_STUDENT);
					}
					if(!roles.contains(SecurityConfig.ROLE_TEACHER)) {
						roles.add(SecurityConfig.ROLE_TEACHER);
					}
				}
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
