package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.FriendRequestDAO;
import model.dto.FriendRequestDTO;

public class FriendRequestAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/error.jsp";
		
		try {
			request.setAttribute("result-friendRequest", 
					FriendRequestDAO.addFriendRequest(
							FriendRequestDTO.builder()
								.senderId(Integer.parseInt(request.getParameter("senderId")))
								.receiverId(Integer.parseInt(request.getParameter("receiverId")))
								.status('p')
								.build()
						));
			url = "home.jsp";
		} catch(SQLException e) {
			System.out.println("hi");
			response.setStatus(400);
			request.setAttribute("errorMsg", "친구 정보 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
