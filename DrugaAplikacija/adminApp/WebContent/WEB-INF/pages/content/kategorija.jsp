<%@page import="net.etfbl.ip.dto.Category"%>
<%@page import="net.etfbl.ip.beans.CategoryBean"%>
<%@page import="net.etfbl.ip.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="categoryBean" class="net.etfbl.ip.beans.CategoryBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Kategorije</title>
</head>
<body>

<%
session.setAttribute("error", "");
if (request.getParameter("submit") != null) {
    String name = request.getParameter("name");
    
    CategoryDAO.insert(new Category(name));
    session.setAttribute("error", "Uspjesno ste dodali kategoriju");
}

%>

<h1 class="text-center">Kategorije</h1>
<div class="container-fluid justify-content-center">
<div class="row align-items-center">
    <div class="col-md-3" style="padding: 25px;">
        <h3>Create category</h3>
        <form action="?action=kategorija" method="POST">
            <label class="form-label">Name</label>
            <input required class="form-control" name="name" type="text" />
            
            <br />
            <input class="btn btn-primary form-control" type="submit" name="submit" value="Create category" />
        <%=session.getAttribute("error").toString() %>
        </form>
    </div>
    <div class="col-md-9 align-items-center">
        <h3>All category</h3>
        <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Action</th>
        </tr>
        
        </thead>
        <tbody>
        <%
            for(Category c : categoryBean.getAllCategories()) {
                out.println("<tr>" +
                "<td>"+ c.getName()+"</td>"+
                "<td><a target='_blank' href='/admin/attributes?id="+c.getId()+"'>Open Attributes</a> | <a href='editCategory?id="+c.getId()+"'>Update</a> | <a href='deleteCategory?id="+c.getId()+"'>Delete</a></td>"+
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