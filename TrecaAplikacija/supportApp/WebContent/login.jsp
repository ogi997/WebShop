<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.etfbl.ip.beans.UserBean" %>
<jsp:useBean id="userBean" class="net.etfbl.ip.beans.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="userManager" class="net.etfbl.ip.service.UserManager" scope="application"></jsp:useBean>
<jsp:setProperty property="username" name="userBean" param="username"></jsp:setProperty>
<jsp:setProperty property="password" name="userBean" param="password"></jsp:setProperty>
<%
	if(request.getParameter("submit") != null) {
		UserBean u = userManager.login(userBean.getUsername(), userBean.getPassword());
		if (u != null) {
			userBean.setFirst_name(u.getFirst_name());
			userBean.setLast_name(u.getLast_name());
			userBean.setLogged(true);
			session.setAttribute("notification", "");
			response.sendRedirect("messages.jsp");
		} else {
			session.setAttribute("notification", "Unijeli ste neispravno korisnicko ime ili lozinku");
			userBean.setLogged(false);
		}
	} else {
		session.setAttribute("notification", "");
	}
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Login - support</title>
</head>
<body>
<div class="container-fluid">

<div style="height: 100vh; display: flex; justify-content: center; align-items: center; flex-direction: column">
<h1 class="text-center">Login page</h1> <br />
<form action="login.jsp" method="post">
	<label class="form-label">Username</label>
	<input required class="form-control" type="text" name="username" />
	
	<label class="form-label">Password</label>
	<input required class="form-control" type="password" name="password" />
	
	<br />
	<input class="btn btn-primary w-100" type="submit" name="submit" value="Login" />
	<%=session.getAttribute("notification").toString() %>
</form>
</div>
</div>
</body>
</html>