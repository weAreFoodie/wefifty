<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jakarta.servlet.http.*, model.dao.UserDAO, model.dto.UserDTO, model.dto.UserSchoolDTO, java.util.ArrayList, model.dao.UserSchoolDAO"%>

<%
    HttpSession sessionObj = request.getSession(false);
    Integer userId = (sessionObj != null) ? (Integer) sessionObj.getAttribute("userIdKey") : null;

    if (userId == null) {
        response.sendRedirect("login.html"); // 로그인하지 않은 경우 로그인 페이지로 이동
        return;
    }

    UserDTO user = UserDAO.getUserByUserId(userId); // DB에서 유저 정보 조회
    ArrayList<UserSchoolDTO> userSchools = UserSchoolDAO.findUserSchoolByUserId(userId); // 학교 정보 조회
    
    String elemSchool = "", middleSchool = "", highSchool = "", university = "";
    int elemGradYear = 0, middleGradYear = 0, highGradYear = 0, uniGradYear = 0;

    for (UserSchoolDTO school : userSchools) {
        switch (school.getSchoolType()) {
            case 'e': elemSchool = school.getSchoolName(); elemGradYear = school.getGradYear(); break;
            case 'm': middleSchool = school.getSchoolName(); middleGradYear = school.getGradYear(); break;
            case 'h': highSchool = school.getSchoolName(); highGradYear = school.getGradYear(); break;
            case 'u': university = school.getSchoolName(); uniGradYear = school.getGradYear(); break;
        }
    }
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
    	<input type="hidden" name="command" value="updateProfile">
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
                
                <!-- 학력 수정 가능 -->
                <div>
                    <label class="block font-semibold">초등학교</label>
                    <div class="flex space-x-2">
                        <input type="text" name="elemSchool" value="<%= elemSchool %>" class="w-2/3 border p-2 rounded-md">
                        <input type="number" name="elemGradYear" value="<%= elemGradYear %>" class="w-1/3 border p-2 rounded-md">
                    </div>
                </div><br>

                <div>
                    <label class="block font-semibold">중학교</label>
                    <div class="flex space-x-2">
                        <input type="text" name="middleSchool" value="<%= middleSchool %>" class="w-2/3 border p-2 rounded-md">
                        <input type="number" name="middleGradYear" value="<%= middleGradYear %>" class="w-1/3 border p-2 rounded-md">
                    </div>
                </div><br>

                <div>
                    <label class="block font-semibold">고등학교</label>
                    <div class="flex space-x-2">
                        <input type="text" name="highSchool" value="<%= highSchool %>" class="w-2/3 border p-2 rounded-md">
                        <input type="number" name="highGradYear" value="<%= highGradYear %>" class="w-1/3 border p-2 rounded-md">
                    </div>
                </div><br>

                <div>
                    <label class="block font-semibold">대학교</label>
                    <div class="flex space-x-2">
                        <input type="text" name="university" value="<%= university %>" class="w-2/3 border p-2 rounded-md">
                        <input type="number" name="uniGradYear" value="<%= uniGradYear %>" class="w-1/3 border p-2 rounded-md">
                    </div>
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
