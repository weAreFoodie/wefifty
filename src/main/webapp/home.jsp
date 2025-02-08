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
    <script src="wefifty.js"></script>
</head>
<body class="bg-gray-100 flex min-h-screen">

    <!-- ### Sidebar ### -->
    <aside class="bg-black flex flex-col items-center py-6 space-y-6 sidebar">
    
    
        <a href="#">
            <img src="images/wefifty_logo.png" alt="">
        </a>
        
        <button onclick="loadView('profile', profileScript)" class="flex flex-col items-center">
            <img src="https://placehold.co/40" class="w-10 h-10 rounded-full border-2 border-white" alt="User profile avatar">
            <p class="text-sm text-white mt-1">김철수 님</p>
        </button>
        
        <button onclick="loadView('recommend', recommendScript)" class="flex flex-col items-center text-white sidebar-btn">
            <i class="fas fa-search fa-2x"></i>
            <span class="text-xs mt-1">친구 찾기</span>
        </button>
        
        <button onclick="loadView('friendList', friendListScript)" class="flex flex-col items-center text-white sidebar-btn">
            <i class="fas fa-users fa-2x"></i>
            <span class="text-xs mt-1">친구 목록</span>
        </button>
        
        
    </aside>
    
    
    <!-- ### MainView ### -->
    <div id="home-mainView" class=" w-screen h-screen ">
       
    </div>
   
</body>
</html>