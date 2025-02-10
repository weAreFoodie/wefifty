<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wefifty</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <link rel="stylesheet" href="wefifty.css">
    <!-- 세션에 저장된 userId를 전역 변수 currentUserId에 할당 -->
    <script>
      const currentUserId = "${sessionScope.userIdKey}";
      // FIXME 테스트용
      //const currentUserId = 1;
    </script>
    <script src="wefifty.js"></script>
    <style>
      /* 사이드바에 높은 z-index 부여 */
      .sidebar {
        position: relative;
        z-index: 1000;
      }
    </style>
</head>
<body class="bg-gray-100 flex min-h-screen">

    <!-- ### Sidebar ### -->
    <aside class="bg-black flex flex-col items-center py-6 space-y-6 sidebar">
    
    
        <a href="#">
            <h1 class="font-bold text-lg" style="color:white;">WEFIFTY</h1>
        </a>

        <button id="nav-profile-view" onclick="loadView('updateProfile', profileScript)" class="flex flex-col items-center">

        </button>
        
        <button onclick="loadFriendRecommendation()" class="flex flex-col items-center text-white sidebar-btn">
		    <i class="fas fa-search fa-2x"></i>
		    <span class="text-xs mt-1">친구 찾기</span>
		</button>
        
        <button onclick="loadView('friendList', friendListScript)" class="flex flex-col items-center text-white sidebar-btn">
            <i class="fas fa-users fa-2x"></i>
            <span class="text-xs mt-1">친구 목록</span>
        </button>
        
        <button onclick="loadView('friendRequestList', friendRequestListScript)" class="flex flex-col items-center text-white sidebar-btn">
            <i class="fas fa-users fa-2x"></i>
            <span class="text-xs mt-1">친구 요청</span>
        </button>
        
        <button onclick="loadView('pointCharging', pointChargingScript)" class="flex flex-col items-center text-white sidebar-btn">
            <i class="fas fa-users fa-2x"></i>
            <span class="text-xs mt-1">포인트 충전</span>
        </button>
        
    </aside>
    
    <!-- sessionScope.userIdKey 체크 후 null일 때 login.html로 이동 -->
    <c:if test="${ empty sessionScope.userIdKey }">
    	<script type="text/javascript">
    		location.href = "login.html";
    	</script>
    </c:if>
    
    <!-- ### MainView ### -->
    <div id="home-mainView" class=" w-screen h-screen ">
       
    </div>
   
<script>
	getNavProfileAction();
</script>
</body>
</html>