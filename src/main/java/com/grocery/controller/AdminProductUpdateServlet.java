package com.grocery.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.service.AdminService;

/**
 * Servlet implementation class AdminProductUpdateServlet
 */
@WebServlet("/AdminProductUpdateServlet")
public class AdminProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AdminService adminService=new AdminService();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int  productId =Integer.parseInt( request.getParameter("productId"));
	        String productName = request.getParameter("productName");
	        double price = Double.parseDouble(request.getParameter("price"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));


	        try {
	            boolean exists = adminService.checkProductExists(productId);
	            if (exists) {
	                adminService.updateProduct(productId, productName, price, quantity);
	                request.setAttribute("message", "Product updated successfully!");
	                request.setAttribute("messageType", "success");
	            } else {
	                request.setAttribute("message", "Product ID does not exist!");
	                request.setAttribute("messageType", "error");
	            }

	            // Forward back to the same page
	            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminProductUpdate.jsp");
	            dispatcher.forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "Error updating product. Please try again.");
	            request.setAttribute("messageType", "error");

	            // Forward back to the same page
	            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminProductUpdate.jsp");
	            dispatcher.forward(request, response);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
