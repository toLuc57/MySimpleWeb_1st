package org.hm.SimpleWeb.beans;

public class Teacher {
	private static final String maPhanChu = "GV";
	
	private String id;
	private String name;
	private String degree;
	private String telephone;
	private String idDepartment;
	
	public Teacher() {
		
	}
	public Teacher(String name,String degree,
			String telephone,String idDepartment) {
		id = null;
		this.setName(name);
		this.setDegree(degree);
		this.setTelephone(telephone);
		this.setIdDepartment(idDepartment);
	}
	public Teacher(String id, String name,String degree,
			String telephone,String idDepartment) {
		this.setId(id);
		this.setName(name);
		this.setDegree(degree);
		this.setTelephone(telephone);
		this.setIdDepartment(idDepartment);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
