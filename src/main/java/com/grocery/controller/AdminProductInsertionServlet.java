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
 * Servlet implementation class AdminProductInsertionServlet
 */
@WebServlet("/AdminProductInsertionServlet")
public class AdminProductInsertionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AdminService adminService=new AdminService();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductInsertionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(  request.getParameter("productId"));
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        try {
            boolean exists =adminService.checkProductExists(productId);
            if (exists) {
                request.setAttribute("message", "Product ID already exists!");
                request.setAttribute("messageType", "error");
            } else {
            		 adminService.insertProduct(productId, productName, price, quantity);
                request.setAttribute("message", "Product added successfully!");
                request.setAttribute("messageType", "success");
            }
            

            // Forward back to the same page
            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminInsertProduct.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error inserting product. Please try again.");
            request.setAttribute("messageType", "error");

            // Forward back to the same page
            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminInsertProduct.jsp");
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
