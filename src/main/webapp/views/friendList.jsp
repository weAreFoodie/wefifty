<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
/* 테이블 제목(the thead) 고정 스타일 */
.fixed-header thead {
	position: sticky;
	top: 0;
	z-index: 10; /* 다른 요소 위에 오도록 설정 */
}

/* 커스텀 스크롤바 스타일 */
.custom-scrollbar::-webkit-scrollbar {
	width: 8px;
}

.custom-scrollbar::-webkit-scrollbar-track {
	background: #ddd;
	border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb {
	background: #aaa;
	border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb:hover {
	background: #888;
}
</style>

<div class="flex justify-between items-center mb-6 mx-16 my-12">
	<h1 class="text-2xl font-bold">친구 목록</h1>
	<div class="flex space-x-4">
		<input type="text" id="searchInput" onkeyup="filterFriends()"
			placeholder="친구 이름으로 검색" class="px-4 py-2 border rounded-md w-80">
		<button onclick="friendListScript()"
			class="bg-blue-500 text-white px-4 py-2 rounded-md">새로고침</button>
			<!-- TODO 새로고침시 피드백 추가 (로딩..?) -->
	</div>
</div>

<div class="bg-white shadow-md rounded-lg overflow-hidden mx-24 my-8 overflow-y-auto custom-scrollbar" style="max-height: calc(100vh - 200px);">
	<table id="friendTable" class="w-full text-left border-collapse fixed-header">
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
				<tr class="border-t hover:bg-gray-100" onclick="getFriendCard('friendList', ${friend.userId})">
					<td class="p-3 flex items-center space-x-3"><c:if
							test="${ empty friend.profilePicture }">
							<img src="https://placehold.co/40" class="w-10 h-10 rounded-full"
								alt="Profile">
						</c:if> <c:if test="${ not empty friend.profilePicture }">
							<img src="${ friend.profilePicture }"
								class="w-10 h-10 rounded-full" alt="Profile">
						</c:if></td>
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