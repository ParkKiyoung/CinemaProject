package com.Manager;

public class BookingBean {
	private int booking_num;
	private String member_userid;
	private String seat;
	private int adult;
	private int youth;
	private String price;
	private String theater_time_ontime;
	private String bookingdate;
	private String movie_subject;
	private String room_roomname;
	
	
	public String getRoom_roomname() {
		return room_roomname == null ? "" : room_roomname.trim();
	}
	public void setRoom_roomname(String room_roomname) {
		this.room_roomname = room_roomname;
	}
	public String getMovie_subject() {
		return movie_subject == null ? "" : movie_subject.trim();
	}
	public void setMovie_subject(String movie_subject) {
		this.movie_subject = movie_subject;
	}
	public String getTheater_time_ontime() {
		return theater_time_ontime == null ? "" : theater_time_ontime.trim();
	}
	public void setTheater_time_ontime(String theater_time_ontime) {
		this.theater_time_ontime = theater_time_ontime;
	}
	public String getMember_userid() {
		return member_userid == null ? "" : member_userid.trim();
	}
	public void setMember_userid(String member_userid) {
		this.member_userid = member_userid;
	}
	public int getBooking_num() {
		return booking_num;
	}
	public String getSeat() {
		return seat == null ? "" : seat.trim();
	}
	public int getAdult() {
		return adult;
	}
	public int getYouth() {
		return youth;
	}
	public String getPrice() {
		return price == null ? "" : price.trim();
	}
	public String getBookingdate() {
		return bookingdate == null ? "" : bookingdate.trim();
	}
	public void setBooking_num(int booking_num) {
		this.booking_num = booking_num;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public void setYouth(int youth) {
		this.youth = youth;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setBookingdate(String bookingdate) {
		this.bookingdate = bookingdate;
	}
}
