<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Pagination and Navigation Buttons -->
<div class="carousel-nav">
	<div class="swiper-pagination"></div>
	<div class="swiper-button-next"></div>
	<div class="swiper-button-prev"></div>
</div>
<div class="w-[90%] max-w-[1200px]">
	<!-- Swiper Container -->
	<div class="swiper-container">
		<div class="swiper-wrapper">


			<!-- Profile Card 1 -->
			<div class="swiper-slide">
				<div class="relative w-full h-[80vh] rounded-3xl shadow-xl overflow-hidden">
					<img src="https://placehold.co/600x400" class="absolute inset-0 object-cover w-full h-full" alt="User profile background">
					<div class="absolute inset-0 bg-black bg-opacity-30"></div>
					<div class="absolute inset-0 p-6 flex flex-col justify-between text-white">
						<div class="flex items-center space-x-3">
							<img src="https://placehold.co/40"
								class="w-10 h-10 rounded-full border-2 border-white"
								alt="User profile avatar">
							<div>
								<p class="text-sm">@user291057</p>
								<p class="text-xs">1976년생</p>
							</div>
						</div>
						<div class="flex items-center justify-between">
							<div>
								<p class="text-sm mb-1">청운대학교 5기 졸업생 모여라</p>
								<h2 class="text-2xl font-semibold">청운대학교 친구입니다.</h2>
							</div>
							<button onclick="friendRequest(1,4)" class="add-member-btn bg-white text-gray-800 px-4 py-2 flex items-center space-x-2 rounded-lg shadow-lg">
								<i class="fas fa-question-circle"></i> 
								<span class="font-medium">
									친구 정보 확인
								</span>
							</button>
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>
</div>