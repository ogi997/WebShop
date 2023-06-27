<%@page import="net.etfbl.ip.dto.User"%>
<%@page import="net.etfbl.ip.beans.UserBean"%>
<%@page import="net.etfbl.ip.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="net.etfbl.ip.beans.UserBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Korisnici</title>
</head>
<body>

<%
session.setAttribute("error", "");
if (request.getParameter("submit") != null) {
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String city = request.getParameter("city");
	String email = request.getParameter("email");
	
	String file = request.getParameter("file");
	System.out.println("file: " + file);
	int result = UserDAO.insert(new User(firstName, lastName, username, password, city, email));
	if (result == -1) {
		session.setAttribute("error", "Korisnicko ime vec postoji.");
	} else {
		session.setAttribute("error", "Uspjesno ste dodali korisnika.");
	}
}

%>

<h1 class="text-center">Korisnici</h1>
<div class="container-fluid justify-content-center">
<div class="row align-items-center">
	<div class="col-md-3" style="padding: 25px;">
		<h3>Create users</h3>
		<form action="?action=korisnici" method="POST">
			<label class="form-label">Firstname*</label>
			<input required class="form-control" name="firstName" type="text" />
			
			<label class="form-label">Lastname*</label>
			<input required class="form-control" name="lastName" type="text" />
			
			<label class="form-label">Username*</label>
			<input required class="form-control" name="username" type="text" />
			
			<label class="form-label">Password*</label>
			<input required class="form-control" name="password" type="password" />
			
			<label class="form-label">City*</label>
			<input required class="form-control" name="city" type="text" />
			
			<label class="form-label">Email*</label>
			<input required class="form-control" name="email" type="email" />
				
			<br />
			<input class="btn btn-primary form-control" type="submit" name="submit" value="Create user" />
			
		<%=session.getAttribute("error").toString() %>
		</form>
	</div>
	<div class="col-md-9 align-items-center">
		<h3>All users</h3>
		<table class="table">
		<thead>
		<tr>
			<th scope="col">Firstname</th>
			<th scope="col">Lastname</th>
			<th scope="col">Username</th>
			<th scope="col">City</th>
			<th scope="col">Email</th>
			<th scope="col">Action</th>
		</tr>
		
		</thead>
		<tbody>
		<%
			for(User u : userBean.getAllUsers()) {
				out.println("<tr>" +
				"<td>"+ u.getFirst_name()+"</td>"+
				"<td>"+ u.getLast_name()+"</td>"+
				"<td>"+ u.getUsername()+"</td>"+
				"<td>"+ u.getCity() +"</td>"+
				"<td>"+ u.getEmail()+"</td>"+
				"<td><a href='editUser?id="+u.getId()+"'>Update</a> | <a href='deleteUser?id="+u.getId()+"'>Delete</a></td>"+
				"</tr>");
			}
		
		%>
		</tbody>
		</table>
	</div>
	
	</div>
	</div>

</body>
</html>