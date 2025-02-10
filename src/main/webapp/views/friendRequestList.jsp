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
	<h1 class="text-2xl font-bold">친구 요청</h1>
	<button onclick="friendRequestListScript()"
		class="bg-green-500 text-white px-4 py-2 rounded-md">새로고침</button>
</div>

<div class="flex items-start space-x-6 mx-24 my-8">
	<!-- 왼쪽 테이블 -->
	<div class="w-1/2">
		<h2 class="text-lg font-semibold mb-3">내가 보낸 요청</h2>
		<div
			class="bg-white shadow-md rounded-lg overflow-hidden overflow-y-auto custom-scrollbar"
			style="max-height: calc(100vh - 200px);">
			<table class="w-full text-left border-collapse fixed-header">
				<thead class="bg-gray-200">
					<tr>
						<th class="p-3"></th>
						<th class="p-3">이름</th>
						<th class="p-3">상태</th>
					</tr>
				</thead>

				<tbody class="">
					<c:forEach items="${requestScope.sendFriendList}" var="send">
						<tr class="border-t hover:bg-gray-100"
							onclick="getFriendCard('friendRequestList', ${send.userId})">
							<td class="p-3 flex items-center space-x-3"><c:if
									test="${ empty send.profilePicture }">
									<img src="https://placehold.co/40"
										class="w-10 h-10 rounded-full" alt="Profile">
								</c:if> <c:if test="${ not empty send.profilePicture }">
									<img src="${ send.profilePicture }"
										class="w-10 h-10 rounded-full" alt="Profile">
								</c:if></td>
							<td class="p-3">${send.name}</td>
							<td class="p-3"><c:choose>
									<c:when test="${send.status.toString() == 'p'}">
										<span
											class="bg-yellow-100 text-yellow-700 px-3 py-1 rounded-full text-sm font-semibold">
											⏳ 대기중 </span>
									</c:when>
									<c:when test="${send.status.toString() == 'r'}">
										<span
											class="bg-red-100 text-red-700 px-3 py-1 rounded-full text-sm font-semibold">
											❌ 거절됨 </span>
									</c:when>
									<c:when test="${send.status.toString() == 'a'}">
										<span
											class="bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm font-semibold">
											✅ 수락됨 </span>
									</c:when>
								</c:choose></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- 오른쪽 테이블 -->
	<div class="w-1/2">
		<h2 class="text-lg font-semibold mb-3">내가 받은 요청</h2>
		<div
			class="bg-white shadow-md rounded-lg overflow-hidden overflow-y-auto custom-scrollbar"
			style="max-height: calc(100vh - 200px);">
			<table class="w-full text-left border-collapse fixed-header">
				<thead class="bg-gray-200">
					<tr>
						<th class="p-3"></th>
						<th class="p-3">이름</th>
						<th class="p-3 text-center">요청</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.receiveFriendList}" var="receive">
						<tr class="border-t hover:bg-gray-100"
							onclick="getFriendCard('friendRequestList', ${receive.userId})">
							<td class="p-3 flex items-center space-x-3"><c:if
									test="${ empty receive.profilePicture }">
									<img src="https://placehold.co/40"
										class="w-10 h-10 rounded-full" alt="Profile">
								</c:if> <c:if test="${ not empty receive.profilePicture }">
									<img src="${ receive.profilePicture }"
										class="w-10 h-10 rounded-full" alt="Profile">
								</c:if></td>
							<td class="p-3">${receive.name}</td>
							<td class="p-3">
								<div class="flex justify-end items-center space-x-2">
									<button
										onclick="updateFriendRequest('a', ${receive.requestId})"
										class="bg-green-500 text-white px-4 py-2 rounded-md">수락</button>
									<button
										onclick="updateFriendRequest('r', ${receive.requestId})"
										class="bg-red-500 text-white px-4 py-2 rounded-md">거절</button>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>