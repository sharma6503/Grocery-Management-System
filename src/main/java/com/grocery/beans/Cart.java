package com.grocery.beans;

public class Cart {
	
	private int productId;
	private String productName;
	private int quantity;
	private double pricePerUnit;

	
	public Cart(int productId, String productName, int quantity, double pricePerUnit) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}


	@Override
	public String toString() {
		return "Cart [producId=" + productId + ", productName=" + productName + ", quantity=" + quantity
				+ ", pricePerUnit=" + pricePerUnit + "]";
	}


	public int getProductId() {
		return productId;
	}


	public void setProducId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
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


	public double getPricePerUnit() {
		return pricePerUnit;
	}


	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	

}
