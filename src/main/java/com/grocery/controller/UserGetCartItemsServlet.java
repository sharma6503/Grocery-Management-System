package com.grocery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.beans.Cart;
import com.grocery.service.UserService;

/**
 * Servlet implementation class UserGetCartItemsServlet
 */
@WebServlet("/UserGetCartItemsServlet")
public class UserGetCartItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  UserService userService=new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserGetCartItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int userId =(int) request.getSession().getAttribute("userId");
        List<Cart> cartItems = userService.getCartItems(userId);
        System.out.println("cartServlet"+cartItems);
        
		request.setAttribute("cartProducts", cartItems);
		request.getRequestDispatcher("UserCart.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
