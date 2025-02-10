package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserSchoolDAO;
import model.dto.FriendInfoDTO;
import model.dto.UserSchoolSummaryDTO;

public class FriendRecommendationAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/error.jsp";
		
		// 세션에 저장된 userId 가져오기
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userIdkey");
		
		// FIXME 테스트용
//		int userId = 1;
		
		try {
			// 해당 userId의 (학교 이름, 졸업년도) 리스트 받아오기 (회원이 속한 학교들의 이름과 졸업년도 받아오기)
			ArrayList<UserSchoolSummaryDTO> schoolSummaryList = UserSchoolDAO.findUserSchoolSummaryByUserId(userId);

			ArrayList<FriendInfoDTO> list;
			if (schoolSummaryList == null || schoolSummaryList.isEmpty()) {
				list = null;
				request.setAttribute("errorType", "noSchoolInfo");
			} else {
				//범위를 만들어주기 위한 gap 값 설정하기
				int gap = 2;
				
				// (학교이름, 졸업년도) 범위값, 유저 아이디 로 해당 범위에 있는 회원의 특정 user 정보 받아오기
				list = UserSchoolDAO.findFriendsBySchoolAndGradYear(schoolSummaryList, gap, userId);
				
				if (list == null || list.isEmpty()) {
					// 추천 로직을 만족하는 친구가 없는 경우에 (recommend 화면)으로 정상적으로 이동후 가능한 친구 추천이 없음을 알리기
					request.setAttribute("errorType", "noRecommendedFriend");
				} 
			}
			request.setAttribute("resultFriendRecommendation", list);
			url = "views/recommendation.jsp";
		} catch (SQLException e) {  // (error 화면)으로 이동
			response.setStatus(400);
			request.setAttribute("errorMsg", "친구 추천 요청 중에 문제가 발생했어요.");
			e.printStackTrace();
		} 
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
