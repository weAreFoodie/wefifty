package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.FriendRequestDAO;
import model.dto.FriendRequestDTO;
import model.dto.ReceiveFriendDTO;
import model.dto.SendFriendDTO;

public class GetFriendRequestListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/error.jsp";

		// 세션에서 userId 가져오기
		HttpSession session = request.getSession();

		// FIXME 세션 구현 전 임시 코드
		// int userId = (int)session.getAttribute("userId");
		int userId = 3;

		try {
			// 유저아이디로 보낸 요청 찾기
			ArrayList<SendFriendDTO> sendFriendList = FriendRequestDAO.findFriendRequestsBySenderId(userId);
			request.setAttribute("sendFriendList", sendFriendList);

			// 유저아이디로 받은 요청 찾기
			ArrayList<ReceiveFriendDTO> receiveFriendList = FriendRequestDAO.findFriendRequestsByReceiverId(userId);
			request.setAttribute("receiveFriendList", receiveFriendList);

			url = "views/friendRequestList.jsp";
		} catch (SQLException e) {
			response.setStatus(400);
			request.setAttribute("errorMsg", "친구 목록 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
