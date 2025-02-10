<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class=" h-[100vh] overflow-scroll flex flex-col items-center mt-12">
	<div class="flex justify-between items-center mb-6 w-[90%]">
		<h1 class="text-2xl font-bold">친구 목록</h1>
		<div class="flex space-x-4">
			<input type="text" id="searchInput" onkeyup="filterFriends()"
				placeholder="친구 이름으로 검색" class="px-4 py-2 border rounded-md w-80">
			<button onclick="friendListScript()"
				class="bg-green-500 text-white px-4 py-2 rounded-md">새로고침</button>
		</div>
	</div>
	
	<div class="bg-white shadow-md rounded-lg  w-[90%]">
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
						<td class="p-3 flex items-center space-x-3"><img
							src="https://placehold.co/40" class="w-10 h-10 rounded-full"
							alt="Profile"></td>
						<td class="p-3">${friend.name}</td>
						<c:if test="${friend.gender.toString() == 'm'}">
							<td class="p-3">남자</td>
						</c:if>
						<c:if test="${friend.gender.toString() == 'f'}">
							<td class="p-3">여자</td>
						</c:if>
						<td class="p-3">${friend.phone}</td>
						<td class="p-3">${friend.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>