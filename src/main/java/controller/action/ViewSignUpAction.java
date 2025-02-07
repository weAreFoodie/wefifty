package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
		
		UserDTO user = new UserDTO().builder()
				.email(request.getParameter("email"))
				.pwd(request.getParameter("pwd"))
				.nickname(request.getParameter("nickname"))
				.bio(request.getParameter("bio"))
				.gender(request.getParameter("gender").charAt(0)) // String -> char 갑 변환
				.phone(request.getParameter("phone"))
				.birth(LocalDate.parse(request.getParameter("birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.profilePicture(request.getParameter("profilePicture"))
				.build();

		// user 입력 데이터가 비어 있지 않으면
		if(user!=null) {
			try {
				int userId = UserDAO.addUser(user);
				if (userId > 0) {  // userId가 정상적으로 생성되었을 경우만 실행
					String elemSchool = request.getParameter("elemSchool"); // 초등학교
					String middleSchool = request.getParameter("middleSchool"); // 중학교
					String highSchool = request.getParameter("highSchool"); // 고등학교
					String university = request.getParameter("university"); // 대학교

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
				    UserSchoolDAO.insertUserSchools(userId, schools);
				}
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "친구 정보 요청 중에 문제가 발생했습니다.");
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}
	

}
