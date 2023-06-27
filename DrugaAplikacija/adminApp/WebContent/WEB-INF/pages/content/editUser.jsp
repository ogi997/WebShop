<%@page import="net.etfbl.ip.dto.User" %>
<%@page import="net.etfbl.ip.beans.UserBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="userBean" class="net.etfbl.ip.beans.UserBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
    <%
    Object admin = session.getAttribute("admin");
    if (admin == null)
    	response.sendRedirect("/admin/");
    %>
<%
	String getId = request.getParameter("id");
	if (getId != null) {
		Integer id = Integer.parseInt(getId);
		User user = userBean.getUserId(id);
		request.setAttribute("userEdit", user);
	}
%>

<%
System.out.println("updejtuj: " + request.getAttribute("update"));
	if (request.getAttribute("submit") != null) {
		System.out.println("UPDATE");
	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>EDIT USER</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="text-center">
		<h1>Edit users info</h1>
	</div>
	<hr />
	<div class="container-fluid">
	<div class="col-md-12">
	<form action="editUser" method="post">
		<input name="hiddenId" type="hidden" value="${userEdit.getId()}" />
		
		<label class="form-label">Firstname</label>
		<input required name="editFirstName" class="form-control" type="text" value="${userEdit.getFirst_name()}" />
		
		<label class="form-label">Lastname</label>
		<input required name="editLastName" class="form-control" type="text" value="${userEdit.getLast_name()}" />
		
		<label class="form-label">Username</label>
		<input required name="editUsername" class="form-control" type="text" value="${userEdit.getUsername()}" />
		
		<label class="form-label">Password</label>
		<input required name="editPassword" class="form-control" type="password" value="${userEdit.getPassword() }" />
		
		<label class="form-label">City</label>
		<input required name="editCity" class="form-control" type="text" value="${userEdit.getCity()}" />
		
		<label class="form-label">Email</label>
		<input required name="editEmail" class="form-control" type="email" value="${userEdit.getEmail()}" />
		
		<br />
		<input class="btn btn-primary" type="submit" name="update" value="UPDATE" />
	
	</form>
	</div>
	</div>
</body>
</html>