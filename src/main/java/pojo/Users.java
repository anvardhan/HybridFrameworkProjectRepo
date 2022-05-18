package pojo;

public class Users {
	
	int id;
	String name;
	String email;
	String gender;
	String status;
	
	public Users(int id, String name, String email, String gender, String status) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
