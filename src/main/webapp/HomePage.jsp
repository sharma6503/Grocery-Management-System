<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Grocery Page</title>
</head>
<body class="text-white vh-100"
	style="background: url('https://img.freepik.com/free-photo/supermarket-banner-concept-with-ingredients_23-2149421147.jpg?t=st=1737216545~exp=1737220145~hmac=278729a52f9fe7615e7a1ffb07f3d74adba8ea12d49657cb028aafadf3181e2f&amp;w=1060') no-repeat center center/cover;">

	<h1 class="mt-4 display-6 text-center text-black">Grocery
		Management System</h1>


	<div class="container mt-5 d-flex  justify-content-center ">


		<div class="card  p-5   border-info "
			style="width: 30rem; height: 25rem;">
			<div class="card-body ms-4">
				<h1 class="display-6">Welcome to Our Application</h1>
				<p class="lead">Select an option below to proceed:</p>

				<span> <a class="btn border-info me-4" href="UserLogin.jsp">User
						Login</a> <a class="btn  border-warning" href="AdminLogin.jsp">Admin
						Login</a>
				</span> <br class="mt-3"> <span> <a
					class="btn border-info  mt-3 me-1" href="UserRegistration.jsp">Register
						User</a> <a class="btn border-warning ms-1  mt-3"
					href="AdminRegistration.jsp">Register Admin</a>
				</span>

			</div>
		</div>


	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
	crossorigin="anonymous"></script>
</html>
