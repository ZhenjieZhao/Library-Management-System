package com.business;

import java.sql.Date;

public class Loan {
	private int loanid;
	private String isbn;
	private String title;
	private String bname;
	private int cardid;
	private Date dateout;
	private Date duedate;
	private Date datein;
	
	
	public Loan(int loanid, String isbn, String title, String bname, int cardid, Date dateout, Date duedate) {
		super();
		this.loanid = loanid;
		this.isbn = isbn;
		this.title = title;
		this.bname = bname;
		this.cardid = cardid;
		this.dateout = dateout;
		this.duedate = duedate;
	}
	public Loan(String isbn, String title, String bname, int cardid, Date dateout, Date duedate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.bname = bname;
		this.cardid = cardid;
		this.dateout = dateout;
		this.duedate = duedate;
	}
	public Loan(String isbn, int cardid, Date dateout, Date duedate, Date datein) {
		super();
		this.isbn = isbn;
		this.cardid = cardid;
		this.dateout = dateout;
		this.duedate = duedate;
		this.datein = datein;
	}
	public Loan(String isbn, int cardid, Date dateout, Date duedate) {
		super();
		this.isbn = isbn;
		this.cardid = cardid;
		this.dateout = dateout;
		this.duedate = duedate;
		this.datein = null;
	}
	public Loan(String isbn, int cardid, Date dateout) {
		super();
		this.isbn = isbn;
		this.cardid = cardid;
		this.dateout = dateout;
	}
	public int getLoanid() {
		return loanid;
	}
	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public Date getDateout() {
		return dateout;
	}
	public void setDateout(Date dateout) {
		this.dateout = dateout;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getDatein() {
		return datein;
	}
	public void setDatein(Date datein) {
		this.datein = datein;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	

}
