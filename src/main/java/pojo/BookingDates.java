package pojo;

public class BookingDates {
	
	/*
	"bookingdates": {
    "checkin": "2023-03-20",
    "checkout": "2023-04-05"
	} */
	
	//Note - Variable/parameter name must match name as in json
	String checkin;
	String checkout;
	
	public BookingDates(String checkin, String checkout) {
		
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}	
	
}
