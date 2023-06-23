package edu.spring.ex01.domain;



public class ProductVO {
	private String name;
	private int price;
	
	public ProductVO() {
		System.out.println("생성자");
	
	}
	public ProductVO(String name, int price) {
		System.out.println("매개변수 생성자");
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getprice() {
		return price;
	}
	public void setAge(int price) {
		this.price =price;
	}
	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
	
	
}
