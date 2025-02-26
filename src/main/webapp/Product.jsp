<%@ page import="java.util.*" %>
<%@ page import="com.grocery.beans.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url('https://tse4.mm.bing.net/th/id/OIP.SJgVGp1Y6M-vrtgnePC3LgHaE7?rs=1&pid=ImgDetMain') no-repeat center center fixed;
            background-size: cover;
        }

        .content { padding-top: 60px; }
        .card { margin-bottom: 20px; }
        .SearchBar { margin: 10px auto; padding: 10px; }

        .fade-out {
            animation: fadeOut 1s forwards;
        }

        @keyframes fadeOut {
            from {
                opacity: 1;
            }
            to {
                opacity: 0;
            }
        }
    </style>
</head>
<body>

<jsp:include page="Navbar.jsp" />

<div class="container content">
    <!-- Display Cart Action Message -->
    <% 
        String message = (String) request.getAttribute("cartActionMessage");
        if (message != null) { 
    %>
    <div id="cart-message" class="alert alert-success text-center" role="alert">
        <%= message %>
    </div>
    <% } %>

    <!-- Search Bar -->
    <div class="SearchBar text-center">
        <form action="ProductSearchServlet" method="GET" class="d-flex justify-content-center">
            <input type="text" id="searchBox" name="productName" class="form-control me-2 w-50"
                placeholder="Enter search term...">
            <button type="submit" class="btn btn-success">Search</button>
        </form>
    </div>

    <!-- Product Grid -->
    <div class="row mt-3">
        <% 
        List<Product> products = (List<Product>) request.getAttribute("productList");
        String userRole = (String) session.getAttribute("role"); // Get the user role from session

        if (products != null && !products.isEmpty()) {
            for (Product product : products) { 
        %>
        <div class="col-lg-3 col-md-4 col-sm-6 mb-4 product-card">
            <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                    <h5 class="card-title productName"><%= product.getName() %></h5>

                    <% if ("admin".equalsIgnoreCase(userRole)) { %>
                        <!-- Show Product ID only for admin users -->
                        <p class="card-text"><strong>Product Id:</strong> <%= product.getProduct_id() %></p>
                    <% } %>

                    <p class="card-text"><strong>Price:</strong> Rs. <%= product.getPrice() %></p>
                    <p class="card-text"><strong>Available Quantity:</strong> <%= product.getQuantity() %></p>

                    <% if (!"admin".equalsIgnoreCase(userRole)) { %> <!-- Show Add to Cart for non-admin users -->
                    <form action="UserAddToCartServlet" method="GET">
                        <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                        <input type="hidden" name="pname" value="<%= product.getName() %>">
                        <input type="hidden" name="price" value="<%= product.getPrice() %>">
                        <div class="input-group mb-3">
                            <input type="number" name="quantity" class="form-control" placeholder="Enter quantity"
                                min="1" max="<%= product.getQuantity() %>" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Add to Cart</button>
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
        <% 
            } 
        } else { 
        %>
        <div class="col-12">
            <p class="display-6 fw-semibold text-center text-white">No products available at the moment.</p>
        </div>
        <% } %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    // Automatically hide the cart message after 4 seconds
    setTimeout(function () {
        var cartMessage = document.getElementById('cart-message');
        if (cartMessage) {
            cartMessage.classList.add('fade-out'); // Add fade-out effect
            setTimeout(() => cartMessage.style.display = 'none', 1000); // Hide after fade-out
        }
    }, 2000); // Show for 4 seconds
</script>
</body>
</html>
