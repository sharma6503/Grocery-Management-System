package com.grocery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.service.UserService;

@WebServlet("/UserAddToCartServlet")
public class UserAddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        try {
            // Get product details from request
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("pname");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Get user ID from session
            int userId = (int) request.getSession().getAttribute("userId"); // Assuming the user is logged in

            // Add product to cart
            boolean success = userService.addToCart(userId, productId, productName, quantity, price);

            // Set the success message
            String message = success ? "Product added to cart successfully!" : "Failed to add product to cart.";
            request.setAttribute("cartActionMessage", message);

            // Forward to the JSP
            request.getRequestDispatcher("get-products").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page in case of exceptions
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
