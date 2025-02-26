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
 * Servlet implementation class AdminDeleteProductServlet
 */
@WebServlet("/AdminDeleteProductServlet")
public class AdminDeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService =new AdminService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            boolean exists = adminService.checkProductExists(productId);
            if (exists) {
                adminService.deleteProduct(productId);
                request.setAttribute("message", "Product deleted successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Product ID does not exist!");
                request.setAttribute("messageType", "error");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDeleteProduct.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error deleting product. Please try again.");
            request.setAttribute("messageType", "error");


            RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDeleteProduct.jsp");
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
