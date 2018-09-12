package com.business;

public class Book {
	private String isbn = null;
	private String title = null;
	private String author = null;	
	private Boolean avail = true;
	
	public Book(String isbn, String title, String author, Boolean avail) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.avail = avail;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Boolean getAvail() {
		return avail;
	}
	public void setAvail(Boolean avail) {
		this.avail = avail;
	}

}
