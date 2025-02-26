package com.grocery.controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grocery.service.UserService;

@WebServlet(name = "login", value = "/login-user")

public class UserLoginServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private static  UserService userService=new UserService();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    HttpSession session = request.getSession(true);
	   
	    
	    boolean loggedIn = userService.loginUser(email, password);

	    if (loggedIn) {
	    	session.setAttribute("role", "user");
	        int userId = userService.getUserId(email);
	        session.setAttribute("userId", userId);
	        session.setAttribute("UserEmail", email);
	        response.sendRedirect("get-products");
	    } else {
	        request.setAttribute("errorMessage", "User not found. Please check your credentials and try again.");
	        request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
	    }
	}

}
