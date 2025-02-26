package com.grocery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.service.UserService;
  
/**
 * Servlet implementation class UserUpdateCartQuantity
 */
@WebServlet("/UserUpdateCartQuantity")
public class UserUpdateCartQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  UserService userService=new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateCartQuantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  int userId = Integer.parseInt(request.getParameter("userId"));
	        int productId = Integer.parseInt(request.getParameter("productId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));

	        boolean success = userService.updateCartItemQuantity(userId, productId, quantity);
			response.getWriter().println(success ? "Cart item updated successfully" : "Failed to update cart item");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
