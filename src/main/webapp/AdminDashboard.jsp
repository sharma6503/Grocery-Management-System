<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background:white ;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .dashboard-container {
            background-color:white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        .dashboard-container ul {
            list-style-type: none;
            padding: 0;
            margin: 20px 0;
        }

        .dashboard-container li {
            background-color: #fff;
            margin: 10px 0;
            padding: 12px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease;
        }

        .dashboard-container li:hover {
            background-color: #f1f1f1;
        }

        .dashboard-container a {
            text-decoration: none;
            color: #333;
            font-size: 16px;
            display: block;
        }

        .dashboard-container a:hover {
            color: #007bff;
        }

        /* Button-like appearance for links */
        .dashboard-container li a {
            padding: 10px 15px;
            border-radius: 5px;
            background-color: #007bff;
            color: white;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .dashboard-container li a:hover {
            background-color: #0056b3;
        }

        /* Styling for the logout link */
        .dashboard-container #logout {
            background-color: #dc3545;
        }

        .dashboard-container #logout:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
	<jsp:include page="Navbar.jsp" />
    <div class="dashboard-container">
        <h1>Welcome, Admin!</h1>
        <ul>
            <li><a href="get-products">View All Products</a></li>
            <li><a href="AdminInsertProduct.jsp">Insert Products</a></li>
            <li><a href="AdminProductUpdate.jsp">Update a Product</a></li>
            <li><a href="AdminDeleteProduct.jsp">Delete a Product</a></li>
            <li><a id="logout" href="AdminLogin.jsp">Logout</a></li>
        </ul>
    </div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>
