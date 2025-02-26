package com.grocery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.beans.Admin;
import com.grocery.service.AdminService;

/**
 * Servlet implementation class AdminDetailsUpdate
 */
@WebServlet("/AdminDetailsUpdate")
public class AdminDetailsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService=new AdminService();     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDetailsUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String email = "ssh@tcs.com";
        String email = (String) request.getSession().getAttribute("AdminEmail");  // Get the email from the session
        System.out.println(email);
        if (email != null) {
            Admin admin = adminService.getAdminByEmail(email);  
           
            System.out.println(admin);
            
            // Fetch admin details based on email
            request.setAttribute("admin", admin);
//            System.out.print(admin.getAdminId());
            request.getRequestDispatcher("AdminProfile.jsp").forward(request, response);
            
        } else {
            response.sendRedirect("AdminLogin.jsp");  // Redirect to login if no email in session
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("admin_id");
    	if(value==null || value.trim().isEmpty())
    	{
    		throw new IllegalArgumentException("Id is missing or empty");
    	}
        int adminId = Integer.parseInt(request.getParameter("admin_id"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = new Admin(adminId, name, phoneNumber, email, password);
        boolean isUpdated = adminService.updateAdmin(admin);

        if (isUpdated) {
            request.setAttribute("message", "Profile updated successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Failed to update profile.");
            request.setAttribute("messageType", "error");
        }

        request.setAttribute("admin", admin);
        request.getRequestDispatcher("AdminProfile.jsp").forward(request, response);
	}

}
