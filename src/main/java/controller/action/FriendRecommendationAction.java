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
import model.dto.FriendInfoDTO;
import model.dto.UserSchoolDTO;
import model.dto.UserSchoolSummaryDTO;

public class FriendRecommendationAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		
		// 세션에 저장된 userId 가져오기
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userIdkey");
		
		try {
			// 해당 userId의 (학교 이름, 졸업년도) 리스트 받아오기 (회원이 속한 학교들의 이름과 졸업년도 받아오기)
			ArrayList<UserSchoolSummaryDTO> schoolSummaryList = UserSchoolDAO.findUserSchoolSummaryByUserId(userId);
			
			if (schoolSummaryList == null || schoolSummaryList.isEmpty()) {
				throw new Exception("학교 정보를 최소한 1개 이상 입력해주세요.");
			}
			
			//범위를 만들어주기 위한 gap 값 설정하기
			int gap = 2;
			
			// (학교이름, 졸업년도) 범위값, 유저 아이디 로 해당 범위에 있는 회원의 특정 user 정보 받아오기
			ArrayList<FriendInfoDTO> list = UserSchoolDAO.findFriendsBySchoolAndGradYear(schoolSummaryList, gap, userId);
			
			if (list == null) {
				request.setAttribute("errorMsg", "가능한 친구 추천이 없습니다.");
			} else {
				request.setAttribute("result-friendRecommendation", list);
				url = "home.jsp";
			}
		} catch (SQLException e) {
			response.setStatus(400);
			request.setAttribute("errorMsg", "친구 추천 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			response.setStatus(400);
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
