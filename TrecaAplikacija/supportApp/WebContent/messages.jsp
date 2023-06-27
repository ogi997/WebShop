<%@page import="net.etfbl.ip.beans.MessageBean"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="messages" class="net.etfbl.ip.service.MessageManager" scope="application"></jsp:useBean>
    <jsp:useBean id="userManager" class="net.etfbl.ip.service.UserManager" scope="session"></jsp:useBean>
    <jsp:useBean id="userBean" class="net.etfbl.ip.beans.UserBean" scope="session"></jsp:useBean>
<%
	if(!(userBean.isLogged())) response.sendRedirect("login.jsp");
%>
<%
	List<MessageBean> msgs = messages.getAllMessages(); 
%>

<%
	if (request.getParameter("searchSubmit") != null){
		System.out.println("Search: " + request.getParameter("search"));
		msgs = messages.getMessageSearch(request.getParameter("search"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Messages</title>
</head>
<body>
<div class="container-fluid">
<div>
	<ul class="nav">
		<li class="nav-items"><a class="nav-link" href="logout.jsp">logout</a></li>
	</ul>
	
</div>
<hr />
<h1>All messages</h1>
<div class="container-fluid">



<form action="messages.jsp" method="post" class="form-inline">

<div class="input-group mb-3">
  <input class="form-control" type="text" placeholder="Pretrazi po sadrzaju..." name="search"/>
  <div class="input-group-append">
    <input class="btn btn-primary" type="submit" name="searchSubmit" value="Search" />
  </div>
</div>

</form>
</div>
<div class="table-responsive">
<table class="table">
  <thead>
    <tr>
      <th scope="col">Username</th>
      <th scope="col">Poruka</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  <%
	for (MessageBean mb : msgs) {
		out.println("<tr>"+
		"<td>"+ (userManager.getUsernameById(mb.getFk_u())).getUsername() + "</td>"+
		"<td style='max-width: 100px; overflow: hidden; text-overflow:ellipsis; white-space: nowrap;'>"+ mb.getText() +"</td>"+
		"<td><a href='message.jsp?id="+mb.getId()+"'>Otvori</a></td>"+
		"</tr>");
		//out.println("Username: " + (userManager.getUsernameById(mb.getFk_u())).getUsername() + "<br />");
		//out.println("Poruka: " + mb.getText() + "<br />");
		//out.println("<a href='message.jsp?id="+mb.getId()+"'>Otvori</a> <br />");
		//out.println("<hr />");
	}
%>
  </tbody>
</table>
</div>
</div>
</body>
</html>