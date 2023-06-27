<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
    Object admin = session.getAttribute("admin");
    if (admin == null)
    	response.sendRedirect("/admin/");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>Log</title>
</head>
<body>
<div class="container-fluid justify-content-center">
<h1 class="text-center">Logs file backend WebShop app</h1>
<hr />

<%
	BufferedReader reader = new BufferedReader(new FileReader("/home/ognjen/Desktop/Internet programiranje/projekat/PrvaAplikacija/backend/IPBackend/logs/spring.log"));
    StringBuilder sb = new StringBuilder();
    String line;

    while((line = reader.readLine())!= null){
    	sb.append(line+"\n");
	}
    out.println(sb.toString());
    reader.close();
%>
</div>
</body>
</html>