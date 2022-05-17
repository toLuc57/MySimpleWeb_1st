package org.hm.SimpleWeb.beans;

import java.sql.Date;

public class Student {
	private static final String maPhanChu = "SV";
	
	private String id;
	private String lastName;
	private String firstName;
	private Date birthday;
	private String sex;
	private String telephone;
	private String address;
	private String idDepartment;

	public Student() {
		
	}
	public Student(String lastName, String firstName, Date birthday, 
			String sex, String telephone,String address, String idDepartment) {
		id = null;
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.birthday = birthday;
		this.setSex(sex);
		this.setTelephone(telephone);
		this.setAddress(address);
		this.setIdDepartment(idDepartment);
	}
	public Student(String id,String lastName, String firstName, Date birthday, 
			String sex,String telephone,String address,String idDepartment) {
		this.setId(id);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.birthday = birthday;
		this.setSex(sex);
		this.setTelephone(telephone);
		this.setAddress(address);
		this.setIdDepartment(idDepartment);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(String idDepartment) {
		this.idDepartment = idDepartment;
	}
	public static String getMaPhanChu() {
		return maPhanChu;
	}
}
