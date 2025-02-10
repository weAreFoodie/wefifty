package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.FriendRequestDAO;
import model.dto.FriendRequestDTO;

public class UpdateFriendRequestAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/error.jsp";

		try {
			request.setAttribute("result-UpdateFriendRequest", FriendRequestDAO.updateFriendRequest(
					Integer.parseInt(request.getParameter("requestId")), request.getParameter("decision").charAt(0)));
			url = "home.jsp";
		} catch (SQLException e) {
			response.setStatus(400);
			request.setAttribute("errorMsg", "요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
