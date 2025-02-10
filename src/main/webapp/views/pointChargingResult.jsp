<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 세션에서 충전된 금액 가져오기
    HttpSession sessionUser = request.getSession();
    Integer amount = (Integer) sessionUser.getAttribute("chargedAmount");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포인트 충전 완료</title>

    <!-- TailwindCSS & SweetAlert -->
    <script src="https://cdn.tailwindcss.com"></script>

    <style>
        body {
            background: url('https://source.unsplash.com/random/1920x1080?money') no-repeat center center fixed;
            background-size: cover;
        }
        
        #home-mainView {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .container-box {
            max-width: 1800px;
            width: 110%;
            padding: 60px;
            background: rgba(0, 0, 0, 0.7);
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            color: white;
            text-align: center;
        }
    </style>
</head>
<body class="flex min-h-screen justify-center items-center">
	<div id="home-mainView">
	    <div class="container-box">
	        <h2 class="text-3xl font-bold mb-4">포인트 충전 완료!</h2>
	        <p class="text-lg mb-6">성공적으로 포인트가 충전되었습니다.</p>
	
	        <!-- 충전된 포인트 정보 -->
	        <div class="text-xl font-semibold mb-6">
	            <p>💰 충전된 금액: <span class="text-yellow-400"><%= amount %></span> 원</p>
	        </div>
	    </div>
	</div>
</body>
</html>
