<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--c:out ; c:forEach etc.-->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!-- formatting (dates -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Book Club</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/style.css">
	<script type="text/javascript" src="/script.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </head>
<body>
	<div class="header">
	<div class="header_line_group">
	    <h1>Welcome! ${user.userName}</h1>
	    <a href="/logout">Logout</a>
	</div>
	<div class="header_line_group">
	    <p>Join our growing community</p>
	    <a href="/books/new">+ Add to my shelf!</a>
	</div>
	</div>
	<table class="table table-dark table-striped-columns box_sizing">
	  <thead class="thead-dark">
	    <tr>
	    	<th>ID</th>
	      	<th>Title</th>
 		    <th>Author Name</th>
 		    <th>Posted By</th>   
	   </tr>
	  </thead>
	  <tbody>
    		<c:forEach var="usersWithBooks" items="${user.books}">
    		<tr>
			<td>${books.id}</td>
			<td>${books.authorName}</td>
			<td>${books.comments}</td>
			<td>${user.userName}</td>			
    		</tr>	
    		</c:forEach>		
	  </tbody>
	</table> 	

    
</body>
</html>
