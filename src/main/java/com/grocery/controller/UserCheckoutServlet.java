package com.grocery.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.beans.Transactions;
import com.grocery.beans.User;
import com.grocery.service.UserService;

@WebServlet("/UserCheckoutServlet")
public class UserCheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static UserService userService = new UserService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCheckoutServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        String email=(String) request.getSession().getAttribute("UserEmail");


        HashMap<Boolean, List<Transactions> >checkoutProductsAndStatus=userService.checkoutCart(userId);
        // If checkout is successful, proceed with forwarding data to the JSP
        List<Transactions> checkoutProducts= null;
        boolean success=false;
        User userName=userService.getUserByEmail(email);
        System.out.println(userName);
        for (Map.Entry<Boolean ,List<Transactions>>cs:checkoutProductsAndStatus.entrySet())
        { 
        	success = cs.getKey();
        // Retrieve the products from the cart for checkout
        	checkoutProducts =cs.getValue();
        		}

        if (success) {
            // Set the checkout products and success message in the request
        	request.setAttribute("user", userName.getUserName());
            request.setAttribute("latestTransaction", checkoutProducts);
            request.setAttribute("message", "Checkout successful! Your transaction is complete.");
        } else {
            // If checkout failed, set a failure message
            request.setAttribute("message", "Checkout failed! Please try again.");
        }

        // Forward to the checkout.jsp page
        request.getRequestDispatcher("/Checkout.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
