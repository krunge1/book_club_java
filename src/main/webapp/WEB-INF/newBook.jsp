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
	    <h1>Add a Book to Your Shelf!</h1>
	    <a href="/books">Back to the Shelves</a>
	</div>
	</div>
	<div class="form_box">
	<form:form action="/books/create" method="post" modelAttribute="book">
   		<div class="mb-3 row">    		
   		<form:label path="title" class="form-label col">Title:</form:label>
   		<form:errors path="title" class="text-danger"/>
   		<form:input path="title" class="col"/>
   		</div>
   		<div class="mb-3 row">
   		<form:label path="authorName" class="form-label col">Author:</form:label>
   		<form:errors path="authorName" class="text-danger"/>
   		<form:input path="authorName" class="col"/>   
   		</div> 		
   		<div class="mb-3 row">
   		<form:label path="comments" class="form-label col">My Thoughts:</form:label>
   		<form:errors path="comments" class="text-danger"/>
   		<form:textarea path="comments" rows="3" class="col"/>
   		</div>
   		<div class="mb-3 row">
 		<form:input path="user" type="hidden" value="${user.id}"/>
	 	<button type="submit" class="btn btn-primary" value="submit_form">Submit</button>
	 	</div>
    </form:form>
	</div>
</body>
</html>
