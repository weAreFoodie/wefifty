<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>친구 추천</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
	    .swiper-button-next, .swiper-button-prev {
	        font-size: 50px !important; 
	        width: 80px !important;     
	        height: 160px !important;    
	        display: flex !important;   
	        align-items: center;
	        justify-content: center;
	        z-index: 1000 !important;     
    	}

	    .swiper-button-next::after, .swiper-button-prev::after {
	        font-size: 50px !important;  
	        color: rgba(0, 0, 0, 0.5) !important;    
	        font-weight: bold;
	    }    
	    
	    .swiper-button-next {
	        right: 20px !important;
	    }

	    .swiper-button-prev {
	        left: 20px !important;
	    }
	</style>
</head>
<body class="bg-gray-100 flex min-h-screen justify-center items-center">

<!-- Swiper 컨테이너 -->
<div class="w-[90%] max-w-[1200px]">
    <div class="swiper-container">
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
                                                <!--  성별 변환 (f → 여, m → 남) -->
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
                                        <button class="add-member-btn bg-white text-gray-800 text-2xl px-8 py-4 flex items-center space-x-2 rounded-lg shadow-lg"
                                                data-userid="${friend.userId}">
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
				                    <a href="editProfile.jsp" class="mt-6 px-6 py-3 text-white text-2xl font-medium bg-red-400 rounded-lg shadow-md hover:bg-red-500 transition-all">
				                        학교 정보 입력하기
				                    </a>
				                </div>
				            </c:when>
				
				            <c:when test="${requestScope.errorType eq 'noRecommendedFriend'}">
				                <div class="bg-blue-100 bg-opacity-80 backdrop-blur-lg p-10 rounded-3xl shadow-lg flex flex-col items-center text-center">
				                    <i class="fas fa-user-friends text-blue-500 text-8xl mb-4 animate-pulse"></i>
				                    <p class="text-black-600 text-4xl font-semibold">아쉽게도 추천할 친구가 없어요!</p>
				                    <p class="text-gray-700 text-2xl mt-4">더 많은 정보를 입력하면 친구 추천이 가능할 수도 있습니다.</p>
				                    <a href="editProfile.jsp" class="mt-6 px-6 py-3 text-white text-2xl font-medium bg-blue-400 rounded-lg shadow-md hover:bg-blue-500 transition-all">
				                        프로필 수정하기
				                    </a>
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
    <div class="swiper-button-next"></div>
    <div class="swiper-button-prev"></div>
    <div class="swiper-pagination"></div>
</div>

<!-- Swiper 및 버튼 이벤트 스크립트 -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        new Swiper(".swiper-container", {
            loop: false,
            spaceBetween: 20,
            slidesPerView: 1,
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            },
        });

        // 친구 요청 버튼 클릭 이벤트
        document.querySelectorAll(".add-member-btn").forEach(button => {
            button.addEventListener("click", function () {
                const friendId = this.getAttribute("data-userid");

                Swal.fire({
                	width: "700px",
                	title: "<h2 style='font-size: 30px; font-weight: bold;'>친구 정보 요청</h2>",
                    html: "<p style='font-size: 25px;'>해당 멤버가 요청을 수락하면 정보확인이 가능합니다.</p> <br> <p style='font-size: 25px; font-weight: bold;'>500 포인트가 차감됩니다.</p>",
                    icon: "question",
                    showCancelButton: true,
                    confirmButtonText: "<span style='font-size: 25px; font-weight: bold;'>너 맞니?</span>",
                    cancelButtonText: "<span style='font-size: 25px;'>아 착각했네</span>"
                }).then((result) => {
                    if (result.isConfirmed) {
                        // AJAX를 사용하여 친구 요청 보내기
                        fetch('friendRequestServlet', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify({ friendId: friendId })
                        }).then(response => response.json())
                          .then(data => {
                              if (data.success) {
                                  Swal.fire("요청 완료!", "친구 요청이 성공적으로 전송되었습니다.", "success");
                              } else {
                                  Swal.fire("요청 실패!", "잠시 뒤에 다시 시도해 주세요.", "error");
                              }
                          }).catch(error => console.error('Error:', error));
                    }
                });
            });
        });
    });
</script>

</body>
</html>
