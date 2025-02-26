package com.grocery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.service.UserService;

/**
 * Servlet implementation class Test
 */
@WebServlet("/register")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static  UserService userService=new UserService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String phoneNumber = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");




			boolean isRegistered = userService.addUser(name, phoneNumber, email, password);

			if (isRegistered) {
				response.sendRedirect("UserLogin.jsp");
			} else {
				response.sendRedirect("UserRegistration.jsp");
			}
		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
