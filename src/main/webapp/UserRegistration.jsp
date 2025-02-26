<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>User Registration</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
<script>
    function validateForm(event) {
        const password = document.querySelector('input[name="password"]').value;

        // Check if the password length is less than 8
        if (password.length < 8) {
            alert("Password must be at least 8 characters long!");
            event.preventDefault(); // Prevent form submission
        }
    }
</script>
</head>
<body style="background : url('https://img.freepik.com/premium-vector/online-registration-sign-up-with-man-standing-near-user-interface_268404-96.jpg?w=900') no-repeat center center/cover">

    <div class="container d-flex justify-content-center mt-5">

        <div class="card bg-transparent border-0 " style="width: 25rem;">
            <h3 class="text-center fw-semibold display-6">Register User</h3>
            
            <div class="card-body">
                <form class="mb-4" method="post" action="register" onsubmit="validateForm(event)">
                    <div class="form-group mb-4">
                        <label for="username">User name</label> 
                        <input type="text"
                            class="form-control" name="username" required
                            aria-describedby="usernameHelp" placeholder="Enter user name">
                    </div>
                    <div class="form-group mb-4">
                        <label for="phone">Phone number</label> 
                        <input type="text"
                            class="form-control" name="phone" required
                            aria-describedby="phoneHelp" placeholder="Enter Phone number">
                    </div>
                    <div class="form-group mb-4">
                        <label for="email">Email address</label> 
                        <input type="email"
                            class="form-control" name="email" aria-describedby="emailHelp"
                            placeholder="Enter email">
                    </div>
                    <div class="form-group mb-4">
                        <label for="password">Password</label> 
                        <input type="password" required
                            class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary me-3">Sign up</button>
                    <a class="btn btn-secondary" href="HomePage.jsp">Home</a>
                </form>
            </div>
        </div>
    </div>
</body>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
    integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
    crossorigin="anonymous"></script>
</html>
