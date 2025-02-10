<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- User Profile Image -->
<c:if test="${ empty requestScope.userImgUrl }">    
    <img src="https://placehold.co/40" class="w-12 h-12 rounded-full border-2 border-white shadow-md" alt="User profile avatar">
</c:if>
<c:if test="${ not empty requestScope.userImgUrl }">
    <img src="${ requestScope.userImgUrl }" class="w-12 h-12 rounded-full border-2 border-white shadow-md" alt="User profile avatar">
</c:if>

<!-- User Info -->
<div class="mt-2 p-2 bg-white/20 rounded-lg text-center shadow-md">
    <p class="text-base font-semibold text-white">
        <c:choose>
            <c:when test="${ empty requestScope.userName }">김철수 님</c:when>
            <c:otherwise>${ requestScope.userName } 님</c:otherwise>
        </c:choose>
    </p>
    <p class="text-sm font-medium text-yellow-300 mt-1">
        <c:choose>
            <c:when test="${ empty requestScope.userPoint }">0p</c:when>
            <c:otherwise>${ requestScope.userPoint }p</c:otherwise>
        </c:choose>
    </p>
</div>
