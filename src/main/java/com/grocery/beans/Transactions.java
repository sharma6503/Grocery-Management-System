package com.grocery.beans;

public class Transactions {
    //private int transactionId;
    private String productName;
    private int quantity;
    private double price;
    //private double totalAmount;

    // Constructor
    public Transactions(String productName, int quantity, double price) {
        //this.transactionId = transactionId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        //this.totalAmount = totalAmount;
    }

    // Getters
   

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    

    
}


