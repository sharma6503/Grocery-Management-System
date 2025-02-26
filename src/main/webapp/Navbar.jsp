<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Grocery</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Playwrite+VN:wght@100..400&display=swap" 
    rel="stylesheet">
    <style>
        /* Global styles to ensure compatibility across pages */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        nav {
            background-color: #333;
            color: white;
            padding: 1rem;
            display: flex;
            justify-content: space-between;
            font-family: "Kanit", serif;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 1000;
            border-radius: 0; 
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 1rem;
            margin: 0;
            padding: 0.2rem;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        nav ul li a:hover {
            color: #BE3144;
            text-decoration: underline;
            transition: all 0.5s ease;
        }

        /* Add spacing below the navbar to prevent overlap */
        .content {
            margin-top: 80px; /* Adjust to match the navbar height */
        }
    </style>
</head>
<body>
    <nav>
        <div style="font-size: 1.5rem; font-weight: bold;">OG Grocery</div>
        <ul>
            <%
                // Get the role from session
                String role = (String) session.getAttribute("role");
            
                /* String role = "user"; */
                if (role != null) {
                    if ("user".equals(role)) {
            %>

            <li><a href="get-products">Home</a></li>
            <li><a href="UserGetCartItemsServlet">Cart</a></li>
            <li><a href="UserOrderHistoryServlet">MyOrders</a></li>
            <li><a href="UserDetailsUpdate">My Profile</a></li>
            <li><a href="UserLogin.jsp">Logout</a></li>
            <%
                    } else if ("admin".equals(role)) {
            %>
            <li><a href="AdminDashboard.jsp">Home</a></li>
            <li><a href="get-products">Products</a></li>
            <li><a href="AdminDetailsUpdate">My Profile</a></li>
            <li><a href="AdminLogin.jsp">Logout</a></li>
            <%
                    }
                } else {
            %>
            <li><a href="UserLogin.jsp">Login</a></li>
            <%
                }
            %>
        </ul>
        
    </nav>

</body>
</html>
