<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jakarta.servlet.http.*, model.dao.UserDAO, model.dto.UserDTO" %>

<%
    HttpSession sessionObj = request.getSession(false);
    Integer userId = (sessionObj != null) ? (Integer) sessionObj.getAttribute("userId") : null;

    if (userId == null) {
        response.sendRedirect("login.html"); // 로그인하지 않은 경우 로그인 페이지로 이동
        return;
    }

    UserDTO user = UserDAO.getUserByUserId(userId); // DB에서 유저 정보 조회
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 수정 | WEFIFTY</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 text-black">

    <!-- Navigation -->
    <nav class="w-full px-10 py-4 flex justify-between border-b text-sm fixed top-0 left-0 bg-white shadow-md z-10 h-16">
        <div class="font-bold text-lg">WEFIFTY</div>
        <div class="font-bold text-sm">회원 정보 수정</div>
    </nav>

    <!-- Update Form -->
    <form name="updateForm" method="post" action="updateProfile">
        <div class="flex justify-center items-center min-h-screen mt-24">
            <div class="w-full max-w-2xl bg-white p-10 shadow-lg">
                <h2 class="text-3xl font-semibold mb-4">회원 정보 수정</h2>

                <!-- 수정 불가능한 정보 -->
                <div>
                    <label class="block font-semibold">이메일</label>
                    <p class="w-full border p-2 rounded-md bg-gray-100"><%= user.getEmail() %></p>
                    <input type="hidden" name="email" value="<%= user.getEmail() %>">
                </div><br>

                <div>
                    <label class="block font-semibold">이름</label>
                    <p class="w-full border p-2 rounded-md bg-gray-100"><%= user.getName() %></p>
                </div><br>

                <div>
                    <label class="block font-semibold">휴대전화번호</label>
                    <p class="w-full border p-2 rounded-md bg-gray-100"><%= user.getPhone() %></p>
                </div><br>

                <div>
                    <label class="block font-semibold">출생년월일</label>
                    <p class="w-full border p-2 rounded-md bg-gray-100"><%= user.getBirth() %></p>
                </div><br>

                <!-- 수정 가능한 정보 -->
                <div>
                    <label class="block font-semibold">새 비밀번호</label>
                    <input type="password" name="pwd" class="w-full border p-2 rounded-md">
                </div><br>

                <div>
                    <label class="block font-semibold">비밀번호 확인</label>
                    <input type="password" name="confirmPwd" class="w-full border p-2 rounded-md">
                </div><br>

                <div>
                    <label class="block font-semibold">닉네임</label>
                    <input type="text" name="nickname" value="<%= user.getNickname() %>" class="w-full border p-2 rounded-md">
                </div><br>

                <div>
                    <label class="block font-semibold">자기소개</label>
                    <input type="text" name="bio" value="<%= user.getBio() %>" class="w-full border p-2 rounded-md">
                </div><br>

                <!-- 성별 선택 -->
                <div>
                    <label class="block font-semibold">성별</label>
                    <div class="flex space-x-4">
                        <label class="flex items-center">
                            <input type="radio" name="gender" value="m" class="mr-2" <%= (user.getGender() == 'm') ? "checked" : "" %>> 남성
                        </label>
                        <label class="flex items-center">
                            <input type="radio" name="gender" value="f" class="mr-2" <%= (user.getGender() == 'f') ? "checked" : "" %>> 여성
                        </label>
                    </div>
                </div><br>

                <div>
                    <label class="block font-semibold">프로필 사진 URL</label>
                    <input type="text" name="profilePicture" value="<%= user.getProfilePicture() %>" class="w-full border p-2 rounded-md">
                </div><br>

                <!-- 수정하기 버튼 -->
                <div class="flex space-x-4 mt-6">
                    <button type="reset" class="w-1/2 border border-gray-400 py-2 rounded-md">취소</button>
                    <button type="submit" class="w-1/2 bg-blue-500 py-2 rounded-md text-white">수정하기</button>
                </div>
            </div>
        </div>
    </form>

</body>
</html>
