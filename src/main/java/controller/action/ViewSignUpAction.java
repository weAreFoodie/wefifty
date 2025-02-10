package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.dao.UserSchoolDAO;
import model.dto.UserDTO;
import model.dto.UserSchoolDTO;

public class ViewSignUpAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		
		String hashedPassword = BCrypt.hashpw(request.getParameter("pwd"), BCrypt.gensalt());
		
		// 유정 정보 객체 생성
		UserDTO user = new UserDTO().builder()
				.email(request.getParameter("email"))
				.pwd(hashedPassword)
				.nickname(request.getParameter("nickname"))
				.name(request.getParameter("name"))
				.bio(request.getParameter("bio"))
				.gender(request.getParameter("gender").charAt(0)) // String -> char 갑 변환
				.phone(request.getParameter("phone"))
				.birth(LocalDate.parse(request.getParameter("birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.profilePicture(request.getParameter("profilePicture"))
				.build();
		
        // 이메일 중복 확인
        try {
			if (UserDAO.isEmailExists(request.getParameter("email"))) {
			    request.setAttribute("errorMsg", "이미 가입된 이메일입니다.");
			    request.getRequestDispatcher(url).forward(request, response);
			    return;
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}

        // 비밀번호 확인 로직 추가
        if (!request.getParameter("pwd").equals(request.getParameter("confirmPwd"))) {
            request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
		
				
		
		// user 입력 데이터가 비어 있지 않으면
		if(user!=null) {
			try {
				// 생성된 유적 객체 저장 및 id 받아오기
				int userId = UserDAO.addUser(user);
				if (userId > 0) {  // userId가 정상적으로 생성되었을 경우만 실행
					
					// Form에서 입력 받은 초,중,고,대학교 받아오기
					String elemSchool = request.getParameter("elemSchool"); // 초등학교
					String middleSchool = request.getParameter("middleSchool"); // 중학교
					String highSchool = request.getParameter("highSchool"); // 고등학교
					String university = request.getParameter("university"); // 대학교
					
					// 각 학교 졸업 년도 받아오기
					int elemGradYear = Integer.parseInt(request.getParameter("elemGradYear"));
					int middleGradYear = Integer.parseInt(request.getParameter("middleGradYear"));
					int highGradYear = Integer.parseInt(request.getParameter("highGradYear"));
					int uniGradYear = Integer.parseInt(request.getParameter("uniGradYear"));
					
				    List<UserSchoolDTO> schools = new ArrayList<>();
				    
				    if (!elemSchool.isEmpty()) schools.add(new UserSchoolDTO(0, userId, elemSchool, elemGradYear, 'e'));
				    if (!middleSchool.isEmpty()) schools.add(new UserSchoolDTO(0, userId, middleSchool, middleGradYear, 'm'));
				    if (!highSchool.isEmpty()) schools.add(new UserSchoolDTO(0, userId, highSchool, highGradYear, 'h'));
				    if (!university.isEmpty()) schools.add(new UserSchoolDTO(0, userId, university, uniGradYear, 'u'));

				    // 2. userSchool 정보 저장
				    boolean result = UserSchoolDAO.insertUserSchools(userId, schools);
				    
				    if(result) {
				    	url = "login.html"; // 회원가입 성공 시 로그인 페이지로 이동
				    }else {
				    	
				    }
				}
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "친구 정보 요청 중에 문제가 발생했습니다.");
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}
	

}
