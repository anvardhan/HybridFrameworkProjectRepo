package pojo;

public class Address_pojoexample1 {
	
	//Variable must match key name is json file
	int houseNo;
	String Street1;
	String city;
	String state;
	int zip;
	String country;
	
	public Address_pojoexample1(int houseNo, String street1, String city, String state, int zip, String country) {
		
		this.houseNo = houseNo;
		this.Street1 = street1;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet1() {
		return Street1;
	}

	public void setStreet1(String street1) {
		Street1 = street1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
