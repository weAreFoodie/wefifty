package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dao.UserSchoolDAO;
import model.dto.UserSchoolDTO;
import model.dto.UserSchoolSummaryDTO;

public class FriendRecommendationAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		
		//세션에 저장된 userId 가져오기
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userIdkey");
		
		//현재 로그인한 회원의 userId로 회원의 학교정보 중 학교이름, 졸업년도 가져오기
//		ArrayList<UserSchoolSummaryDTO> list = UserSchoolDAO.findUserSchoolSummaryByUserId(userId);
		
		String schoolName; 
		int gradYear;
		int gap;
		
		try {
			request.setAttribute("result-friendRecommendation", null);
			url = "home.jsp";
		} catch(Exception e) {
			request.setAttribute("errorMsg", "친구 추천 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
