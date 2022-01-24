package org.hm.SimpleWeb.beans;

import java.sql.Date;

public class Student {
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
	public Student(String id,String FullName, Date birthday, 
			String sex,String telephone,String address,String idDepartment) {
		this.setId(id);
		String lastName = FullName;
		String firstName = FullName;
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
	public void setId(String maSV) {
		id = maSV;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String hoSV) {
		lastName = hoSV;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String tenSV) {
		firstName = tenSV;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String phai) {
		sex = phai;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date ngaySinh) {
		birthday = ngaySinh;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String dienThoai) {
		telephone = dienThoai;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String diaChi) {
		address = diaChi;
	}
	public String getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(String maKhoa) {
		idDepartment = maKhoa;
	}
}
