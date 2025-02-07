package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendRequestDAO;
import model.dto.FriendRequstDTO;

public class FriendRequestAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		
		try {
			request.setAttribute("result-friendRequest", 
					FriendRequestDAO.addFriendRequest(
							FriendRequstDTO.builder()
								.senderId(0)
								.receiverId(0)
								.status('p')
								.build()
						));
			url = "home.jsp";
		} catch(SQLException e) {
			request.setAttribute("errorMsg", "친구 정보 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
