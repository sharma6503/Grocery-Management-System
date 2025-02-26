<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <title>Admin Login</title>
</head>
<body>
    <div class="container mt-2">
        <div class="row">
            <div class="col-sm">
                <div class="card border-0" style="width: 100%;">
                    <div class="card-body">
                        <img src="https://img.freepik.com/free-vector/admin-concept-illustration_114360-2229.jpg?semt=ais_incoming"
                             alt="Card image cap">
                        <p class="lead text-center">Register yourself by filling the form.</p>
                    </div>
                </div>
            </div>
            <div class="col-sm mt-5 p-5">
                <div class="container mt-5">
                    <h1 class="display-6">Admin Login</h1>
                    
                    <!-- Display error message if present -->
                    <% 
                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null) { 
                    %>
                        <div class="alert alert-danger" role="alert">
                            <%= errorMessage %>
                        </div>
                    <% } %>

                    <form class="mb-4" method="post" action="admin-login">
                        <div class="form-group mb-4">
                            <label for="email">Email address</label> 
                            <input type="email" class="form-control" name="email" aria-describedby="emailHelp"
                                   placeholder="Enter email" required>
                        </div>
                        <div class="form-group mb-4">
                            <label for="password">Password</label> 
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                        </div>
     
                        <button type="submit" class="btn btn-primary me-3">Login</button>
                        <a class="btn btn-secondary" href="HomePage.jsp">Home</a>
                    </form>
                    <p class="small">Enter your credentials to login to the admin dashboard.</p>
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
