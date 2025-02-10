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

public class ViewSignUpAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";

		String hashedPassword = BCrypt.hashpw(request.getParameter("pwd"), BCrypt.gensalt());

		// 유정 정보 객체 생성
		UserDTO user = new UserDTO().builder().email(request.getParameter("email")).pwd(hashedPassword)
				.nickname(request.getParameter("nickname")).name(request.getParameter("name"))
				.bio(request.getParameter("bio")).gender(request.getParameter("gender").charAt(0)) // String -> char 갑
																									// 변환
				.phone(request.getParameter("phone"))
				.birth(LocalDate.parse(request.getParameter("birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.profilePicture(request.getParameter("profilePicture")).build();

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
		if (user != null) {
			try {
				// 생성된 유적 객체 저장 및 id 받아오기
				int userId = UserDAO.addUser(user);
				if (userId > 0) { // userId가 정상적으로 생성되었을 경우만 실행

					// Form에서 입력 받은 초, 중, 고, 대학교 받아오기
					List<UserSchoolDTO> schools = new ArrayList<>();
					addSchoolIfExists(request, "elemSchool", "elemGradYear", 'e', userId, schools);
					addSchoolIfExists(request, "middleSchool", "middleGradYear", 'm', userId, schools);
					addSchoolIfExists(request, "highSchool", "highGradYear", 'h', userId, schools);
					addSchoolIfExists(request, "university", "uniGradYear", 'u', userId, schools);

					// 학교 정보가 있는 경우에만 저장
					if (!schools.isEmpty()) {
						boolean result = UserSchoolDAO.insertUserSchools(userId, schools);
						if (!result) {
							request.setAttribute("errorMsg", "학교 정보 저장 중 오류가 발생했습니다.");
							request.getRequestDispatcher(url).forward(request, response);
							return;
						}
					}
					url = "login.html";
				}
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "회원 가입 중 문제가 발생했습니다.");
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void addSchoolIfExists(HttpServletRequest request, String schoolParam, String yearParam, char schoolType, int userId, List<UserSchoolDTO> schools) {
		String schoolName = request.getParameter(schoolParam);
		String gradYearStr = request.getParameter(yearParam);

		// 학교 이름과 졸업 연도가 모두 입력된 경우만 추가
		if (schoolName != null && !schoolName.isEmpty() && gradYearStr != null && !gradYearStr.isEmpty()) {
			try {
				int gradYear = Integer.parseInt(gradYearStr);
				schools.add(new UserSchoolDTO(0, userId, schoolName, gradYear, schoolType));
			} catch (NumberFormatException e) {
				// 졸업 연도가 숫자가 아닐 경우 무시
				System.err.println("올바르지 않은 졸업 연도 입력: " + gradYearStr);
			}
		}
	}

}
