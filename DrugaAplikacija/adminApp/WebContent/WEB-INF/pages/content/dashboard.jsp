<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="net.etfbl.ip.dto.*" %>
<jsp:useBean id="adminBean" class="net.etfbl.ip.beans.AdminBean" scope="session"></jsp:useBean>
    <%
    Object admin = session.getAttribute("admin");
    if (admin != null)
    	System.out.println("dashboard: " + ((Admin)admin).isLogged());
    else 
    	response.sendRedirect("/admin/");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>ADMIN - DASHBOARD</title>
</head>
<body>

<div class="text-center">
	<h2>Welcome to admin dashboard</h2>

<ul class="nav justify-content-center">
	<li class="nav-item"><a class="nav-link" href="?action=home">Pocetna</a></li>
	<li class="nav-item"><a class="nav-link" href="?action=kategorija">Kategorije</a></li>
	<li class="nav-item"><a class="nav-link" href="?action=korisnici">Korisnici</a></li>
	<li class="nav-item"><a class="nav-link" href="?action=statistika" target="_blank">Statistika</a></li>
	
	<li class="nav-item"><a class="nav-link" href="?action=logout.jsp">Logout</a></li>
</ul>

</div>
<hr />
<div>
	<%
	String router = request.getParameter("action");
	%>
	
	<% if("kategorija".equals(router)) { %>
		<jsp:include page="kategorija.jsp"></jsp:include>
	<%} else if ("korisnici".equals(router)) { %>
		<jsp:include page="korisnici.jsp"></jsp:include>
	<%} else { %>
	<div class="container-fluid text-center">
			<h1 style="margin-top: 50px">Dashboard administratorski panel</h1>
			<h4>Ovdje mozete da upravljanje podacima nad kategorijama kao i njihovim posebnim atributima</h4>
			<h4>Takodje mozete da upravljanje i nad podacima o korisnicima.</h4>
			<br />
			<h4>Vasi podaci:</h4>
			<h5><b>Ime:</b> <i>${admin.getFirst_name()}</i></h5>
			<h5><b>Prezime:</b> <i> ${admin.getLast_name()}</i></h5>
			<h5><b>Korisnicko ime:</b> <i> ${admin.getUsername()}</i></h5>
			<h5></h5>
		</div>
	<% } %>

</div>


</body>
</html>