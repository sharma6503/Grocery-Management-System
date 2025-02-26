package com.grocery.dao;

import java.security.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.grocery.beans.Cart;
import com.grocery.beans.Order;
import com.grocery.beans.Product;
import com.grocery.beans.Transactions;
import com.grocery.beans.User;

public interface UserDao {
	public boolean addUser(String name, String phonenumber, String email, String password);

	public boolean loginUser(String email, String password);

	public List<Cart> getCartProducts(int customerId);

	public int getUserIdFromEmail(String email);

	public List<Product> searchProducts(String productName);

	public boolean updateCartItemQuantity(int userId, int productId, int quantity);

	public List<Cart> getCartItems(int userId);

	public boolean addToCart(int userId, int productId, String pname, int quantity, double price);

	public boolean removeCartItem(int userId, int productId);

	public HashMap<Boolean, List<Transactions>> checkoutCart(int userId);

	public boolean updateUserDetails(User user);

	public User getUserFromEmail(String email);
	public List<Order> getMyOrders(int orderId);
	
}
