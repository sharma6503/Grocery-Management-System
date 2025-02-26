package com.grocery.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grocery.service.AdminService;

@WebServlet(name = "adminLogin", value = "/admin-login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    String email = request.getParameter("email");
		    String password = request.getParameter("password");
		    
		    HttpSession session = request.getSession(true);
		    
		    
		    boolean isAdmin = adminService.adminLogin(email, password);
		    if (isAdmin) {
		    	session.setAttribute("role", "admin");
		    	session.setAttribute("AdminEmail", email);
		        response.sendRedirect("AdminDashboard.jsp");
		    } else {
		        request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
		        try {
		            request.getRequestDispatcher("AdminLogin.jsp").forward(request, response);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}


	}

