<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event List</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script>
        function filterFriends() {
            let input = document.getElementById("searchInput").value.toLowerCase();
            let rows = document.querySelectorAll("#friendTable tbody tr");
            
            rows.forEach(row => {
                let name = row.querySelector("td:nth-child(2)").textContent.toLowerCase();
                
                if (name.includes(input)) {
                    row.style.display = "";
                } else {
                    row.style.display = "none";
                }
            });
        }
    </script>
</head>
<body class="bg-gray-100 p-10">
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-bold">친구 목록</h1>
        <div class="flex space-x-4">
            <input type="text" id="searchInput" onkeyup="filterFriends()" placeholder="친구 이름으로 검색" class="px-4 py-2 border rounded-md w-80">
            <button onclick="location.reload()" class="bg-green-500 text-white px-4 py-2 rounded-md">새로고침</button>
        </div>
    </div>

    <div class="bg-white shadow-md rounded-lg overflow-hidden">
        <table id="friendTable" class="w-full text-left border-collapse">
            <thead class="bg-gray-200">
                <tr>
                    <th class="p-3"></th>
                    <th class="p-3">친구 이름</th>
                    <th class="p-3">성별</th>
                    <th class="p-3">전화번호</th>
                    <th class="p-3">이메일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.friendList}" var="friend">
                    <tr class="border-t hover:bg-gray-100">
                        <td class="p-3 flex items-center space-x-3"><img src="https://placehold.co/40" class="w-10 h-10 rounded-full" alt="Profile"></td>
                        <td class="p-3">${friend.name}</td>
                        <c:if test="${friend.gender.toString() == 'm'}"><td class="p-3">남자</td></c:if>
                        <c:if test="${friend.gender.toString() == 'f'}"><td class="p-3">여자</td></c:if>
                        <td class="p-3">${friend.phone}</td>
                        <td class="p-3">${friend.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
