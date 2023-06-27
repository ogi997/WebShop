<%@page import="net.etfbl.ip.beans.MessageBean"%>
<%@page import="net.etfbl.ip.beans.UserWebBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="messages" class="net.etfbl.ip.service.MessageManager" scope="application"></jsp:useBean>
    <jsp:useBean id="userManager" class="net.etfbl.ip.service.UserManager" scope="session"></jsp:useBean>
    <jsp:useBean id="userBean" class="net.etfbl.ip.beans.UserBean" scope="session"></jsp:useBean>
	<jsp:useBean id="messageManager" class="net.etfbl.ip.service.MessageManager" scope="session"></jsp:useBean>
	
	
<%
//session.setAttribute("messageNotification", "BBB");
String result;
	if (request.getParameter("submit") != null) {
		System.out.println("Poslan je mejl na " + session.getAttribute("email").toString());
		String poruka = request.getParameter("poruka");
		session.setAttribute("porukaZaSlanje", poruka);
		
		response.sendRedirect("SendEmail.jsp");
		   

		 //String to = session.getAttribute("email").toString();

	      
		    
		    
	   }
		
		//response.sendRedirect("messages.jsp");
	
	
%>
<%
//session.setAttribute("messageNotification", "AAA");
System.out.println(userBean.isLogged());
	if(!(userBean.isLogged())) response.sendRedirect("login.jsp");
	
	String action = request.getParameter("id");
	if (action != null) {
		Integer id = Integer.parseInt(action);
		
		MessageBean mb = messageManager.getMessageById(id);
		if (mb == null)
			response.sendRedirect("messages.jsp");
		
		UserWebBean uwb = userManager.getUsernameById(mb.getFk_u());
		if (uwb == null)
			response.sendRedirect("messages.jsp");
		
		session.setAttribute("message", mb);
		session.setAttribute("user", uwb);
		session.setAttribute("email", uwb.getEmail());
		//podesi poruku kao procitanu
		messageManager.updateStatusById(id);
		
	}
	
	System.out.println("ID poruke: " + action);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>Poruka</title>
</head>
<body>
<div class="container-fluid">

<div class="text-center">
<h1>Poruka</h1>
</div>
<hr />


<div class="jumbotron">
  <h2>Info o posiljaocu:</h2>
  <h4 class="lead"><b>Ime:</b> ${user.getFirst_name()}</h4>
  <h4 class="lead"><b>Prezime:</b> ${user.getLast_name()}</h4>
  <h4 class="lead"><b>Korisnicko ime:</b> ${user.getUsername()}</h4>
  <h4 class="lead"><b>Grad:</b> ${user.getCity()}</h4>
  <h4 class="lead"><b>Email:</b> ${user.getEmail()}</h4>
  <hr class="my-4">
  <div class="text-wrap">
  	<h3>Poruka glasi: <i>${message.getText()}</i></h3>
  </div>
</div>

<form action="" method="post">
<textarea required class="form-control" rows="3" cols="25" name="poruka" placeholder="Unesite odgovor"></textarea>
<br />
<input class="btn btn-primary" type="submit" name="submit" value="Posalji odgovor" />
</form>
<br />
<a href="messages.jsp">Vrati se</a>
</div>
</body>
</html>