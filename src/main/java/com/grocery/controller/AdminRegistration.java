
package com.grocery.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.service.AdminService;
@WebServlet(name = "register", value = "/register-admin")
public class AdminRegistration extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static AdminService adminService=new AdminService();
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

       String name = request.getParameter("username");
       String phoneNumber=request.getParameter("phone");  
       String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isRegistered = adminService.adminRegistration(name,phoneNumber,email,password);
        
        if(isRegistered) {
        	response.sendRedirect("AdminLogin.jsp");
        }
        else {
        	response.sendRedirect("AdminRegistration.jsp");
        }
        
        
    }
        

	
}