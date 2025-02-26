<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            background-color: white;
            padding: 20px 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 100%;
            max-width: 400px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
            text-align: left;
        }

        input, button {
            margin-top: 5px;
            padding: 8px;
            width: 100%;
            font-size: 14px;
            box-sizing: border-box;
        }

        button {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 15px;
            border-radius: 4px;
        }

        button:hover {
            background-color: #45a049;
        }

        .message {
            margin-top: 15px;
            padding: 10px;
            border-radius: 5px;
            font-weight: bold;
            text-align: center;
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
    </style>
</head>
<body>
<jsp:include page="Navbar.jsp" />
    <div class="container">
        <h2>Admin Profile</h2>

        <c:if test="${not empty message}">
            <div class="message ${messageType}">
                ${message}
            </div>
        </c:if>

        <p><strong>Name:</strong> ${admin.adminName}</p>
        <p><strong>Phone Number:</strong> ${admin.phoneNumber}</p>
        <p><strong>Email:</strong> ${admin.email}</p>

        <form action="AdminDetailsUpdate" method="post">
            <input type="hidden" name="admin_id" value="${admin.adminId}" />

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${admin.adminName}" required />

            <label for="phoneNumber">Phone Number:</label>
            <input type="number" id="phoneNumber" name="phoneNumber" value="${admin.phoneNumber}" required />


            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${admin.password}" required />

            <button type="submit">Update Profile</button>
        </form>
    </div>
</body>
</html>