package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.FriendRequestDAO;
import model.dto.FriendDTO;

public class GetFriendListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/error.jsp";

		// 세션에서 userId 가져오기
		HttpSession session = request.getSession();

		int userId = (int)session.getAttribute("userIdKey");

		try {
			// 유저아이디로 친구 리스트 찾기
			ArrayList<FriendDTO> friendList = FriendRequestDAO.findFriendListByUserId(userId);

			// 찾은 친구 리스트를 넘겨주기
			request.setAttribute("friendList", friendList);
			url = "views/friendList.jsp";
		} catch (SQLException e) {
			response.setStatus(400);
			request.setAttribute("errorMsg", "친구 목록 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
