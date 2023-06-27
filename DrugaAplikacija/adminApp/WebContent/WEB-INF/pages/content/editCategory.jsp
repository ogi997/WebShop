<%@page import="net.etfbl.ip.dto.Category" %>
<%@page import="net.etfbl.ip.beans.CategoryBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="categoryBean" class="net.etfbl.ip.beans.CategoryBean" scope="session"></jsp:useBean>
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
		Category category = categoryBean.getCategoryId(id);
		request.setAttribute("categoryEdit", category);
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
<title>EDIT CATEGORY</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="text-center">
		<h1>Edit category info</h1>
	</div>
	<hr />
	<div class="container-fluid">
	<div class="col-md-12">
	<form action="editCategory" method="post">
		<input name="hiddenId" type="hidden" value="${categoryEdit.getId()}" />
		
		<label class="form-label">Name</label>
		<input required name="editName" class="form-control" type="text" value="${categoryEdit.getName()}" />
	
		<br />
		<input class="btn btn-primary" type="submit" name="update" value="UPDATE" />
	
	</form>
	</div>
	</div>
</body>
</html>