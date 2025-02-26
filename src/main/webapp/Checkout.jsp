<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.grocery.beans.Transactions" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }

        .home-container {
            border: 1px solid #ddd;
            padding: 20px 30px;
            background: white;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            width: 60%;
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            margin-bottom: 10px;
        }

        p {
            text-align: center;
            margin: 5px 0;
        }

        .invoice-details {
            margin-bottom: 20px;
        }

        .invoice-details strong {
            margin-right: 10px;
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
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            margin: 10px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .buttons {
            text-align: center;
            margin-top: 20px;
        }

        .note {
            text-align: center;
            font-size: 14px;
            margin-top: 20px;
            color: #555;
        }
    </style>
</head>
<body>

<div class="home-container">
    <h1>Transaction Successfully Completed</h1>
    <div class="invoice-details">
        <p><strong>Date:</strong> <%= new java.util.Date() %></p>
        <p><strong>Billed to:</strong><%=  request.getAttribute("user") %></p> <!-- Replace 'John Doe' dynamically if needed -->
    </div>

    <table>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price/Unit</th>
                <th>Total Price</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Transactions> latestTransaction = (List<Transactions>) request.getAttribute("latestTransaction"); 
                String message = (String) request.getAttribute("message");
                if (latestTransaction != null && !latestTransaction.isEmpty()) {
                    for (Transactions product : latestTransaction) { 
            %>
            <tr>
                <td><%= product.getProductName() %></td>
                <td><%= product.getQuantity() %></td>
                <td>Rs. <%= product.getPrice() %></td>
                <td>Rs. <%= product.getQuantity() * product.getPrice() %></td>
            </tr>
            <% 
                    }
                } else if (message != null) { 
            %>
            <tr>
                <td colspan="4"><strong><%= message %></strong></td>
            </tr>
            <% } else { %>
            <tr>
                <td colspan="4">No transactions found.</td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <div class="note">
        <strong>Note:</strong> Thank you for shopping with us.
    </div>

    <div class="buttons">
        <a class="btn" href="get-products">Go back</a>
        <a class="btn" onclick="printPage()">Print</a>
    </div>
</div>

<script>
    function printPage() {
        window.print();
    }
</script>

</body>
</html>
