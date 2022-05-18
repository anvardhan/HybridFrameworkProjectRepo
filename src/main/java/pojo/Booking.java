package pojo;

public class Booking {
	
	/*{
	    "firstname": "Anand",
	    "lastname": "V",
	    "totalprice": 378,
	    "depositpaid": false,
	    "bookingdates": {
	        "checkin": "2023-03-20",
	        "checkout": "2023-04-05"
	    },
	    "additionalneeds": "Breakfast"
	  } */
	
	//Note - Variable/parameter name must match name as in json
	String firstname;
	String lastname;
	int totalprice;
	boolean depositpaid;
	BookingDates bookingdates;
	String additionalneeds;	
	
	public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, 
			String additionalneeds, BookingDates bookingdates) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.additionalneeds = additionalneeds;
		this.bookingdates = bookingdates;
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

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}	
		
}
