<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- img -->
<c:if test="${ empty requestScope.userImgUrl }">	
	<img src="https://placehold.co/40" class="w-10 h-10 rounded-full border-2 border-white" alt="User profile avatar">
</c:if>
<c:if test="${ not empty requestScope.userImgUrl }">
	<img src="${ requestScope.userImgUrl }" class="w-10 h-10 rounded-full border-2 border-white" alt="User profile avatar">
</c:if>


<c:if test="${ empty requestScope.userName }">	
	<p class="text-sm text-white mt-1">김철수 님</p>
</c:if>
<c:if test="${ not empty requestScope.userName }">	
	<p class="text-sm text-white mt-1">${ requestScope.userName } 님</p>
</c:if>


<c:if test="${ empty requestScope.userPoint }">	
	<p class="text-sm text-white mt-1"> 0p </p>
</c:if>
<c:if test="${ not empty requestScope.userPoint }">	
	<p class="text-sm text-white mt-1"> ${ requestScope.userPoint }p </p>
</c:if>



