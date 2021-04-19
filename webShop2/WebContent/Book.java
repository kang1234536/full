package com.kosta.day05;

//자바소스로 사용하지않고 IO연습용입니다.
public class Book {
	private String title;
	private int price;
	public Book() {
		//default
	}
	public Book(String title, int price) {
		super();
		this.title = title;
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}




