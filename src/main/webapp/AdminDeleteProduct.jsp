<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Product</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f4;
        }

        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px;
            margin: 8px 0 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            color: #333;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            border-radius: 4px;
        }

        button:hover {
            background-color: #45a049;
        }

        .message {
            font-size: 16px;
            margin-bottom: 15px;
            font-weight: bold;
            border-radius: 4px;
            padding: 10px;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

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

        /* Optional: Add some responsiveness for smaller screens */
        @media (max-width: 600px) {
            .container {
                width: 90%;
                padding: 15px;
            }
        }
    </style>
</head>
<body>
<jsp:include page="Navbar.jsp"/>

<div class="container">
    <h2>Delete Product</h2>

    <!-- Display messages if available -->
    <% 
        String message = (String) request.getAttribute("message");
        String messageType = (String) request.getAttribute("messageType"); // success or error
    %>
    <% if (message != null) { %>
        <div id="message-box" class="message <%= messageType %>">
            <%= message %>
        </div>
    <% } %>

    <!-- Product Deletion Form -->
    <form action="AdminDeleteProductServlet" method="post">
        <label for="productId">Product ID:</label>
        <input type="text" name="productId" id="productId" required>

        <button type="submit">Delete Product</button>
    </form>
</div>

<script>
    // Automatically hide the message after 4 seconds, then fade out
    setTimeout(function() {
        var messageBox = document.getElementById('message-box');
        if (messageBox) {
            messageBox.classList.add('fade-out'); // Apply fade-out effect
            setTimeout(() => messageBox.style.display = 'none', 1000); // Hide after fade-out
        }
    }, 4000); // Show message for 4 seconds
</script>
</body>
</html>
