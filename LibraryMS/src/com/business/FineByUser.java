package com.business;

public class FineByUser {
	private int cardid;
	private float amount;
	
	
	public FineByUser(int cardid, float amount) {
		super();
		this.cardid = cardid;
		this.amount = amount;
	}
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	

}
