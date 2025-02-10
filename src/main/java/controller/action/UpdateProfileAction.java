package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dao.UserSchoolDAO;
import model.dto.UserDTO;
import model.dto.UserSchoolDTO;

public class UpdateProfileAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        Integer userId = (session != null) ? (Integer) session.getAttribute("userIdKey") : null;
        System.out.println("updateAction " + userId);
        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        try {
        	String password = request.getParameter("pwd");
            String confirmPassword = request.getParameter("confirmPwd");
            String nickname = request.getParameter("nickname");
            String bio = request.getParameter("bio");
            String gender = request.getParameter("gender");
            String profilePicture = request.getParameter("profilePicture");
            
         // 비밀번호 변경 확인
            String hashedPassword = null;
            if (password != null && !password.isEmpty()) {
                if (!password.equals(confirmPassword)) {
                    request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
                    request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                    return;
                }
                hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            }

            // 회원 정보 업데이트
            UserDTO user = UserDAO.getUserByUserId(userId);
            if (user == null) {
                request.setAttribute("errorMsg", "사용자 정보를 찾을 수 없습니다.");
                request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                return;
            }
            
            user.setPwd(hashedPassword != null ? hashedPassword : user.getPwd()); // 비밀번호 변경이 있으면 업데이트
            user.setNickname(nickname);
            user.setBio(bio);
            user.setGender(gender.charAt(0));
            user.setProfilePicture(profilePicture);

            boolean userUpdated = UserDAO.updateUser(user);
            if (!userUpdated) {
                request.setAttribute("errorMsg", "회원 정보 수정에 실패했습니다.");
                request.getRequestDispatcher("updateProfile.jsp").forward(request, response);
                return;
            }
            
         // 학력 정보 업데이트
            List<UserSchoolDTO> existingSchools = UserSchoolDAO.findUserSchoolByUserId(userId);

            updateOrInsertSchool(request, "elemSchool", "elemGradYear", 'e', userId, existingSchools);
            updateOrInsertSchool(request, "middleSchool", "middleGradYear", 'm', userId, existingSchools);
            updateOrInsertSchool(request, "highSchool", "highGradYear", 'h', userId, existingSchools);
            updateOrInsertSchool(request, "university", "uniGradYear", 'u', userId, existingSchools);

            // 업데이트 성공 후 홈 페이지로 이동
            response.sendRedirect("home.jsp");
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	
        }

    }
	
	private void updateOrInsertSchool(HttpServletRequest request, String schoolParam, String yearParam, char schoolType, int userId, List<UserSchoolDTO> existingSchools) throws SQLException {
        String schoolName = request.getParameter(schoolParam);
        int gradYear = parseYear(request.getParameter(yearParam));

        if (schoolName == null || schoolName.isEmpty() || gradYear == 0) return;

        for (UserSchoolDTO school : existingSchools) {
            if (school.getSchoolType() == schoolType) {
                // 기존 정보가 있으면 업데이트
                school.setSchoolName(schoolName);
                school.setGradYear(gradYear);
                UserSchoolDAO.updateUserSchool(school);
                return;
            }
        }

        // 기존 정보가 없으면 새로운 데이터 추가
        UserSchoolDTO newSchool = new UserSchoolDTO(0, userId, schoolName, gradYear, schoolType);
        UserSchoolDAO.addUserSchool(newSchool);
    }

    /**
     * 졸업년도 파싱 (null 체크 및 변환)
     */
    private int parseYear(String yearStr) {
        try {
            return (yearStr != null && !yearStr.isEmpty()) ? Integer.parseInt(yearStr) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
  
}
