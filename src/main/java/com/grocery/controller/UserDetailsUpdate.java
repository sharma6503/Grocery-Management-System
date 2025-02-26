package com.grocery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.grocery.beans.User;
import com.grocery.service.UserService;

/**
 * Servlet implementation class UserDetailsUpdate
 */
@WebServlet("/UserDetailsUpdate")
public class UserDetailsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static  UserService userService=new UserService();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailsUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String email = "cust@gmail.com";
       String email = (String) request.getSession().getAttribute("UserEmail");  // Get the email from the session
HttpSession session=request.getSession();
System.out.println("User mail"+email);
if (email != null) {
            User user = userService.getUserByEmail(email);  // Fetch admin details based on email
            session.setAttribute("user", user);
            System.out.println(user);
//            System.out.print(user.getUserId());
            request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
            
        } else {
            response.sendRedirect("UserLogin.jsp");  // Redirect to login if no email in session
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String value = request.getParameter("userId");
    	if(value==null || value.trim().isEmpty())
    	{
    		throw new IllegalArgumentException("Id is missing or empty");
    	}
        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(userId, name, phoneNumber, email, password);
        boolean isUpdated = userService.updateUser(user);

        if (isUpdated) {
            request.setAttribute("message", "Profile updated successfully!");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Failed to update profile.");
            request.setAttribute("messageType", "error");
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
	}

}
