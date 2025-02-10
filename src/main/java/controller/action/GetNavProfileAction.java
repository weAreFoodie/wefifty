package controller.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class GetNavProfileAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userIdKey");
		try {
			
			UserDTO user = UserDAO.getUserByUserId(userId);
			
			request.setAttribute("userImgUrl", user.getProfilePicture());
			request.setAttribute("userName", user.getName());
			request.setAttribute("userPoint", user.getPoint());
			
			url = "views/navProfile.jsp";
		} catch (SQLException e) {
			response.setStatus(400);
			e.printStackTrace();
            request.setAttribute("errorMsg", "profile 로드 중에 오류가 발생했습니다.");
		}
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
