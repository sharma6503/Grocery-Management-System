package com.grocery.dao;

import com.grocery.beans.Admin;

public interface AdminDao {
	public boolean addAdmin(String name, String phonenumber, String email, String password);

	public boolean loginAdmin(String email, String password);

	public boolean deleteProduct(int productId);

	public boolean updateProduct(int productId, String productName, double price, int quantity);

	public boolean insertProduct(int productId, String productName, double price, int quantity);

	public boolean updateAdmin(Admin admin);

	public Admin getAdminByEmail(String email);
}
