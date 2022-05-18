package pojo;

import java.util.List;

public class UserData_pojoexample1 {
	
	//Variable must match key name is json file
	String firstname;
	String lastname;
	String professional;
	String phone;
	String status;
	boolean available;
	Address_pojoexample1 address;
	
	public UserData_pojoexample1(String firstname, String lastname, String professional, String phone, String status,
			boolean available, Address_pojoexample1 address) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.professional = professional;
		this.phone = phone;
		this.status = status;
		this.available = available;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Address_pojoexample1 getAddress() {
		return address;
	}

	public void setAddress(Address_pojoexample1 address) {
		this.address = address;
	}

	
	
}
