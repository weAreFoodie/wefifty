<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="w-[90%] max-w-[1200px] mx-auto my-6" style="position: relative;">
    <div class="swiper-container">
	<div class="w-[100%]">
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
		<div class="swiper-pagination"></div>
	</div>
        <div class="swiper-wrapper">
            <c:choose>
                <c:when test="${not empty requestScope.resultFriendRecommendation}">
                    <c:forEach var="friend" items="${requestScope.resultFriendRecommendation}">
                        <div class="swiper-slide">
                            <div class="relative w-full h-[80vh] rounded-3xl shadow-xl overflow-hidden">
                                <img src="${empty friend.profilePicture ? 'https://placehold.co/600x400' : friend.profilePicture}"
                                     class="absolute inset-0 object-cover w-full h-full" alt="프로필 배경">
                                <div class="absolute inset-0 bg-black bg-opacity-30"></div>
                                <div class="absolute inset-0 p-6 flex flex-col justify-between text-white">
                                    <div class="flex items-center space-x-3">
                                        <img src="${empty friend.profilePicture ? 'https://placehold.co/40' : friend.profilePicture}"
                                             class="w-20 h-20 rounded-full border-2 border-white" alt="프로필 사진">
                                        <div>
                                            <p class="text-4xl">@${friend.nickname}</p>
                                            <p class="text-3xl">
                                                ${friend.birth.year}년생 
                                                <c:choose>
                                                    <c:when test="${String.valueOf(friend.gender) eq 'f'}"> (여)</c:when>
                                                    <c:when test="${String.valueOf(friend.gender) eq 'm'}"> (남)</c:when>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="flex items-center justify-between">
                                        <div>
                                            <p class="text-3xl mb-1">${friend.schoolName} 동문</p>
                                            <h2 class="text-5xl font-semibold">${friend.name} 님</h2>
                                            <h3 class="text-4xl mt-2 text-gray-300">${friend.bio}</h3>
                                        </div>
                                        <button class="add-member-btn swiper-no-swiping bg-white text-gray-800 text-2xl px-8 py-4 flex items-center space-x-2 rounded-lg shadow-lg" data-userid="${friend.userId}">
                                            <i class="fas fa-question-circle"></i>
                                            <span class="font-medium">친구야 혹시 너니?</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="swiper-slide flex justify-center items-center h-[80vh]">
						<c:choose>
				            <c:when test="${requestScope.errorType eq 'noSchoolInfo'}">
				                <div class="bg-red-100 bg-opacity-80 backdrop-blur-lg p-10 rounded-3xl shadow-lg flex flex-col items-center text-center">
				                    <i class="fas fa-school text-red-500 text-8xl mb-4 animate-bounce"></i>
				                    <p class="text-black-600 text-4xl font-semibold">학교 정보를 입력해주세요!</p>
				                    <p class="text-gray-700 text-2xl mt-4">친구 추천을 받으려면 학교 정보를 입력해야 합니다.</p>
				                </div>
				            </c:when>
				            <c:when test="${requestScope.errorType eq 'noRecommendedFriend'}">
				                <div class="bg-blue-100 bg-opacity-80 backdrop-blur-lg p-10 rounded-3xl shadow-lg flex flex-col items-center text-center">
				                    <i class="fas fa-user-friends text-blue-500 text-8xl mb-4 animate-pulse"></i>
				                    <p class="text-black-600 text-4xl font-semibold">아쉽게도 추천할 친구가 없어요!</p>
				                    <p class="text-gray-700 text-2xl mt-4">더 많은 정보를 입력하면 친구 추천이 가능할 수도 있습니다.</p>
				                </div>
				            </c:when>
				            <c:otherwise>
				                <div class="bg-gray-100 bg-opacity-80 backdrop-blur-lg p-10 rounded-3xl shadow-lg flex flex-col items-center text-center">
				                    <i class="fas fa-exclamation-triangle text-gray-500 text-8xl mb-4 animate-shake"></i>
				                    <p class="text-black-600 text-4xl font-semibold">문제가 발생했습니다!</p>
				                    <p class="text-gray-700 text-2xl mt-4">잠시 후 다시 시도해 주세요.</p>
				                </div>
				            </c:otherwise>
				        </c:choose>
    				</div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>