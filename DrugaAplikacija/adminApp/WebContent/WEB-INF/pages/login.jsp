<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div class="container-fluid">
<div style="display: flex; justify-content: center; align-items: center; flex-direction: column; height: 100vh;">
<h1>Login page</h1>

<form action="?action=login" method="post">
	<label class="form-label">Username</label>
	<input class="form-control" type="text" name="username" placeholder="Enter username" />
	
	<label class="form-label">Password</label>
	<input class="form-control" type="password" name="password" placeholder="Enter password" />
	<br />
	<input class="btn btn-primary w-100" type="submit" name="submit" value="Login" /> 
	
	<%=session.getAttribute("notification").toString()%>
</form>
</div>
</div>
</body>
</html>