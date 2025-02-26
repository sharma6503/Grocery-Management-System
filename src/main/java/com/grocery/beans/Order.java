package com.grocery.beans;


public class Order {
	private int orderId;
	private String productName;
	private int quantity;
	private double price;
	private String timeStamp;

	public Order(int orderId, String productName, int quantity, double price,String timeStamp) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.timeStamp = timeStamp;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productName=" + productName + ", quantity=" + quantity + ", price="
				+ price + ", timeStamp=" + timeStamp + "]";
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	// Method to format orderTime string into desired format


}
