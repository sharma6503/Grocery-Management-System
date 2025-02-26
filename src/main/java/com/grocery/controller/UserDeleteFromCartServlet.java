package com.grocery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.service.UserService;

@WebServlet("/UserDeleteFromCartServlet")
public class UserDeleteFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    	
        int productId = Integer.parseInt(request.getParameter("productId"));
//        System.out.println(productId);
        int userId = (int) request.getSession().getAttribute("userId");

        boolean success = userService.removeCartItem(userId, productId);

        // Set the success/failure message as a request attribute
        String message = success ? "Cart item removed successfully!" : "Failed to remove cart item.";
        request.setAttribute("cartActionMessage", message);

        // Redirect back to the cart page (or the appropriate JSP)
        request.getRequestDispatcher("UserCart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
