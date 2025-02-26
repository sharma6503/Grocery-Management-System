package com.grocery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grocery.beans.Order;
import com.grocery.service.UserService;

/**
 * Servlet implementation class UserOrderHistoryServlet
 */
@WebServlet("/UserOrderHistoryServlet")
public class UserOrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrderHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		List<Order>orders=userService.getMyOrders((int)request.getSession().getAttribute("userId"));
		System.out.println(orders);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("UserOrders.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
