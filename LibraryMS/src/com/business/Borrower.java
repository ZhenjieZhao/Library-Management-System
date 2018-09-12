package com.business;

public class Borrower {
	private int card_id;
	private String Ssn = null;
	private String name = null;
	private String addresss = null;
	private String phone = null;
	
	
	public Borrower(int card_id, String ssn, String name, String addresss, String phone) {
		super();
		this.card_id = card_id;
		Ssn = ssn;
		this.name = name;
		this.addresss = addresss;
		this.phone = phone;
	}
	
	
	
	public Borrower(String ssn, String name, String addresss, String phone) {
		super();
		Ssn = ssn;
		this.name = name;
		this.addresss = addresss;
		this.phone = phone;
	}



	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public String getSsn() {
		return Ssn;
	}
	public void setSsn(String ssn) {
		Ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
