<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.grocery.beans.Cart" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <meta charset="ISO-8859-1">
    <title>Cart Page</title>
</head>
<body>
<jsp:include page="Navbar.jsp" />

<div class="container m-4">xxxx</div>
<div class="container mt-4">
    <!-- Display Cart Action Message -->
    <% 
        String message = (String) request.getAttribute("cartActionMessage");
        if (message != null) { 
    %>
    <div class="alert alert-info text-center" role="alert">
        <%= message %>
    </div>
    <% 
        }
    %>

    <div class="row g-4 mt-4">
        <!-- Cart Items Section -->
        <div class="col-lg-8 mt-4">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h2 class="mb-0">Cart Products</h2>
                </div>
                <div class="card-body">
                    <div class="list-group">
                        <% 
                            List<Cart> cartItems = (List<Cart>) request.getAttribute("cartProducts");
                            if (cartItems != null && !cartItems.isEmpty()) {
                                for (Cart product : cartItems) {
                        %>
                        <div class="list-group-item mb-3 p-3 border rounded shadow-sm">
                            <div class="row align-items-center">
                                <div class="col-md-2">
                                    <strong>Product Id:</strong>
                                    <p class="mb-0"><%= product.getProductId() %></p>
                                </div>
                                <div class="col-md-3">
                                    <strong>Product Name:</strong>
                                    <p class="mb-0"><%= product.getProductName() %></p>
                                </div>
                                <div class="col-md-2">
                                    <strong>Quantity:</strong>
                                    <p class="mb-0"><%= product.getQuantity() %></p>
                                </div>
                                <div class="col-md-3">
                                    <strong>Price:</strong>
                                    <p class="mb-0">Rs. <%= product.getPricePerUnit() %></p>
                                </div>
                                <div class="col-md-2 text-end">
                                    <button type="button" class="btn btn-danger btn-sm"
                                            onclick="window.location.href='UserDeleteFromCartServlet?productId=<%= product.getProductId() %>'">
                                        Delete
                                    </button>
                                </div>
                            </div>
                        </div>
                        <% 
                                }
                            } else { 
                        %>
                        <div class="alert alert-warning text-center" role="alert">
                           Product removed from cart successfully!
                        </div>
                        <% 
                            } 
                        %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Cart Summary Section -->
        <div class="col-lg-4 mt-4">
            <div class="card shadow-sm">
                <div class="card-header bg-success text-white">
                    <h3 class="mb-0">Cart Summary</h3>
                </div>
                <div class="card-body">
                    <% 
                        if (cartItems != null && !cartItems.isEmpty()) {
                            int totalItems = 0;
                            double totalPrice = 0;
                            for (Cart product : cartItems) {
                                totalItems += product.getQuantity();
                                totalPrice += product.getPricePerUnit() * product.getQuantity();
                            }
                    %>
                    <p><strong>Total Items:</strong> <%= totalItems %></p>
                    <p><strong>Total Price:</strong> Rs. <%= totalPrice %></p>
                    <a href="UserCheckoutServlet" class="btn btn-success w-100">Proceed to Checkout</a>
                    <% 
                        } 
                    %>
                </div>
            </div>
        </div>
    </div>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
</body>
</html>
