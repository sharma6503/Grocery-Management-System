package com.grocery.service;

import java.lang.invoke.StringConcatFactory;
import java.util.HashMap;
import java.util.List;

import com.grocery.beans.Product;
import com.grocery.beans.Transactions;
import com.grocery.beans.User;
import com.grocery.dao.UserdaoImpl;
import com.grocery.beans.Cart;
import com.grocery.beans.Order;

public class UserService {

	private static UserdaoImpl userdaoImpl=new UserdaoImpl();
	
	public boolean addUser(String name,String phonenumber,String email, String password) {
		return  userdaoImpl.addUser(name, phonenumber, email, password);
	}
	
	public boolean loginUser(String name,String password) {
		return userdaoImpl.loginUser(name, password);
	}
	
	public List<Product> getAllProducts(){
		return userdaoImpl.getAllProducts();
	}
	
	public List<Product>searchProduct(String productName) {
		return userdaoImpl.searchProducts(productName);
	}
	 public boolean addToCart(int userId, int productId, String pname, int quantity, double price) {
	        return userdaoImpl.addToCart(userId, productId, pname, quantity, price);
	    }

	    public List<Cart> getCartItems(int userId)  {
	        return userdaoImpl.getCartItems(userId);
	    }

	    public boolean updateCartItemQuantity(int userId, int productId, int quantity) {
	        return userdaoImpl.updateCartItemQuantity(userId, productId, quantity);
	    }

	    public boolean removeCartItem(int userId, int productId) {
	        return userdaoImpl.removeCartItem(userId, productId);
	    }
	    
	    public HashMap<Boolean, List<Transactions>> checkoutCart(int userId)  {
	        return userdaoImpl.checkoutCart(userId);
	    }

	    
		public int getUserId(String email) {
			// TODO Auto-generated method stub
			return userdaoImpl.getUserIdFromEmail(email);
		}

		public User getUserByEmail(String email) {
			// TODO Auto-generated method stub
			return userdaoImpl.getUserFromEmail(email);
		}

		public boolean updateUser(User user) {
			// TODO Auto-generated method stub
			return userdaoImpl.updateUserDetails(user);
		}

		public List<Order> getMyOrders(int userId) {
			// TODO Auto-generated method stub
			return userdaoImpl.getMyOrders(userId);
		}


}



