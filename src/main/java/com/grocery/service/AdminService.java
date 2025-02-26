package com.grocery.service;

import com.grocery.beans.Admin;
import com.grocery.dao.AdminDaoImpl;

public class AdminService {
	private static AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

	public boolean adminRegistration(String name, String phonenumber, String email, String password) {
		return adminDaoImpl.addAdmin(name, phonenumber, email, password);
	}

	public boolean adminLogin(String email, String password) {
		return adminDaoImpl.loginAdmin(email, password);
	}

	public boolean deleteProduct(int productId) {
		return adminDaoImpl.deleteProduct(productId);

	}

	public boolean checkProductExists(int productId) {
		return adminDaoImpl.checkProductExists(productId);
	}

	public boolean updateProduct(int productId, String productName, double price, int quantity) {
		return adminDaoImpl.updateProduct(productId,productName,price,quantity);

	}

	public boolean insertProduct(int productId, String productName, double price, int quantity) {
		// TODO Auto-generated method stub
		return adminDaoImpl.insertProduct(productId, productName, price, quantity);
				
	}

	public Admin getAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return adminDaoImpl.getAdminByEmail(email);
	}

	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDaoImpl.updateAdmin(admin);
				
	}
}
