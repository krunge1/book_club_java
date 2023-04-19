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
	    <h1>${book.title}</h1>
	    <a href="/books">Back to the Shelves</a>
	</div>
	</div>
	<div class="form_box">
	<div class="mb-3 row border">
	<c:choose>
		<c:when test="${book.user.id == userId}">
			<c:out value="You read ${book.title} by ${book.authorName}"/>
		</c:when>
		<c:otherwise>
			<c:out value="${book.user.userName} read ${book.title} by ${book.authorName}"/>
		</c:otherwise>
	</c:choose>
	</div>
	<p class="mb-3 row border">Here are ${book.user.userName}'s thoughts:</p>
	<p class="mb-3 row border">${book.comments}</p>
			<div class="display_flex">
	<c:choose>
		<c:when test="${book.user.id == userId}">
				<form action="/books/${book.id}/edit">
				<button type="submit" class="btn btn-primary" value="submit_form">Edit</button>
				</form>
				<form action="/books/${book.id}/delete">
				<button type="submit" class="btn btn-danger" value="submit_form">Delete</button>
				</form>
		</c:when>
	</c:choose>
			</div>
	</div>
</body>
</html>
