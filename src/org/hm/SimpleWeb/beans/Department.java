package org.hm.SimpleWeb.beans;

public class Department {
	private String id;
	private String name;
	private String address;
	private String telephone;
	
	public Department() {
	}
	public Department(String name, String address, 
			String telephone) {
		id = null;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}
	public Department(String id, String name, String address, 
			String telephone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
