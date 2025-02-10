<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="flex justify-center items-center min-h-screen">
	<div
		class="relative w-full h-[80vh] rounded-3xl shadow-xl overflow-hidden mx-8">
		<img
			src="${empty requestScope.friend.profilePicture ? 'https://placehold.co/600x400' : friend.profilePicture}"
			class="absolute inset-0 object-cover w-full h-full" alt="프로필 배경">
		<div class="absolute inset-0 bg-black bg-opacity-30"></div>

		<button
			class="absolute top-4 right-4 bg-gray-800 bg-opacity-70 text-white text-2xl font-bold px-4 py-2 rounded-full hover:bg-gray-700 transition z-50"
			onclick="loadView('${param.originPage}', ${param.originPage}Script)">
			X
		</button>

		<div
			class="absolute inset-0 p-6 flex flex-col justify-between text-white">
			<div class="flex items-center space-x-3">
				<img
					src="${empty requestScope.friend.profilePicture ? 'https://placehold.co/40' : friend.profilePicture}"
					class="w-20 h-20 rounded-full border-2 border-white" alt="프로필 사진">
				<div>
					<p class="text-4xl">@${requestScope.friend.nickname}</p>
					<p class="text-3xl">${requestScope.friend.birth.year}년생</p>
				</div>
			</div>
			<div class="flex items-center justify-between">
				<div>
					<p class="text-3xl mb-1">${requestScope.friend.schoolName}동문</p>
					<h2 class="text-5xl font-semibold">${requestScope.friend.name}님</h2>
					<h3 class="text-4xl mt-2 text-gray-300">${requestScope.friend.bio}</h3>
				</div>
			</div>
		</div>
	</div>
</div>