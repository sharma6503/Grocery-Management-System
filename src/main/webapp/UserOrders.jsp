<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.grocery.beans.Order" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #ece9e6, #ffffff);
            margin: 0;
            padding: 20px;
        }

        .orders-container {
            max-width: 80%;
            margin: auto;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px;
        }

        .orders-header {
            text-align: center;
            padding: 15px;
            background: #007bff;
            color: #fff;
            font-size: 24px;
            border-radius: 10px 10px 0 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f9;
            font-weight: bold;
        }

        td {
            font-size: 16px;
        }

        .no-orders {
            text-align: center;
            font-size: 18px;
            color: #555;
            margin-top: 20px;
        }

        .btn-container {
            text-align: center;
            margin-top: 20px;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin: 5px;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="orders-container">
    <div class="orders-header">User Order History</div>

    <% 
        List<Order> orders = (List<Order>) request.getAttribute("orders");

        if (orders != null && !orders.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price (Rs.)</th>
                <th>Total Price (Rs.)</th>
                <th>Order Date & Time</th>
            </tr>
        </thead>
        <tbody>
            <%
/*                 // Define the date-time format for input and output
                SimpleDateFormat dateTimeFormatInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                SimpleDateFormat dateTimeFormatOutput = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); */

                for (Order order : orders) { 
                    // Ensure the order timeStamp is correctly formatted and not null
                    /* String timeStampStr = order.getTimeStamp(); // Getting the timeStamp String
                    Date orderDate = null;

                    // Parse the timeStamp String into Date
                    try {
                        if (timeStampStr != null && !timeStampStr.isEmpty()) {
                            orderDate = dateTimeFormatInput.parse(timeStampStr);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();  // Handle any parse errors here
                    } */

                    // Format the timestamp to "yyyy-MM-dd HH-mm-ss" format
                    /* String formattedOrderTime = (orderDate != null) ? dateTimeFormatOutput.format(orderDate) : "Invalid Timestamp"; */
            %>
            <tr>
                <td><%= order.getOrderId() %></td>
                <td><%= order.getProductName() %></td>
                <td><%= order.getQuantity() %></td>
                <td><%= order.getPrice() %></td>
                <td><%= order.getQuantity() * order.getPrice() %></td>
                <td><%= order. getTimeStamp() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% 
        } else { 
    %>
    <p class="no-orders">No orders found for this user.</p>
    <% } %>

    <div class="btn-container">
        <a class="btn" href="get-products">Continue Shopping</a>
        <a class="btn" href="javascript:void(0);" onclick="printPage()">Print Order History</a>
    </div>
</div>

<script>
    function printPage() {
        window.print();
    }
</script>

</body>
</html>
