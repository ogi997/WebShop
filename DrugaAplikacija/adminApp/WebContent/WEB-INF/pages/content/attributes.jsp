<%@page import="net.etfbl.ip.dto.Attribute"%>
<%@page import="net.etfbl.ip.beans.AttributeBean"%>
<%@page import="net.etfbl.ip.dao.AttributeDAO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="attributeBean" class="net.etfbl.ip.beans.AttributeBean" scope="session"/>
    <%
    Object admin = session.getAttribute("admin");
    if (admin == null)
    	response.sendRedirect("/admin/");
    %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Attributes</title>
</head>
<body>

<%
List<Attribute> attributes = new ArrayList<>();
String categoryId = request.getParameter("id");
Integer id = null;
session.setAttribute("categoryId", null);
if (categoryId != null) {
	id = Integer.parseInt(categoryId);
	session.setAttribute("categoryId", id);
	attributes = attributeBean.getAllAttributes(id);
}

session.setAttribute("error", "");
if (request.getParameter("submit") != null) {
    String name = request.getParameter("name");
    String getId = request.getParameter("id");
    Integer id1 = Integer.parseInt(getId);
    
    AttributeDAO.insert(new Attribute(name, id1));
    session.setAttribute("error", "Uspjesno ste dodali kategoriju");
}

%>

<h1 class="text-center">Atributi</h1>
<div class="container-fluid justify-content-center">
<div class="row align-items-center">
    <div class="col-md-3" style="padding: 25px;">
        <h3>Create attribute</h3>
        <form action="/admin/attributes?id=${categoryId }" method="POST">
            <label class="form-label">Name</label>
            <input required class="form-control" name="name" type="text" />
            
            <br />
            <input class="btn btn-primary form-control" type="submit" name="submit" value="Create category" />
        <%=session.getAttribute("error").toString() %>
        </form>
    </div>
    <div class="col-md-9 align-items-center">
        <h3>All attributes</h3>
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Action</th>
        </tr>
        
        </thead>
        <tbody>
        <%
            for(Attribute a : AttributeDAO.selectAll(id)) {
                out.println("<tr>" +
                "<td>"+ a.getName()+"</td>"+
                "<td><a href='editAttribute?id="+a.getId()+"'>Update</a> | <a href='deleteAttribute?id="+a.getId()+"'>Delete</a></td>"+
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