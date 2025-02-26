package com.grocery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.grocery.beans.Product;
import com.grocery.service.UserService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(name = "getProducts", value = "/get-products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static UserService userService = new UserService();

    public ProductServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set cache control headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies

        // Fetch the product list
        List<Product> productList = userService.getAllProducts();

        // Set the product list as a request attribute
        request.setAttribute("productList", productList);

        // Forward to the JSP page
        request.getRequestDispatcher("Product.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
