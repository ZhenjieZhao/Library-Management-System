package com.business;

public class Fine {
	private int loan_id;
	private float amount = 0;
	private boolean paid = false;
	
	
	
	public Fine(int loan_id, float amount, boolean paid) {
		super();
		this.loan_id = loan_id;
		this.amount = amount;
		this.paid = paid;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	

}
