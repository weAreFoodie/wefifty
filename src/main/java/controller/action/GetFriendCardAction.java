package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.FriendRequestDAO;
import model.dao.UserDAO;
import model.dto.FriendDTO;
import model.dto.FriendInfoDTO;
import model.dto.UserDTO;

public class GetFriendCardAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/error.jsp";
		
		int userId = Integer.parseInt(request.getParameter("friendId"));

		try {
			// 유저아이디로 친구 리스트 찾기
			UserDTO user = UserDAO.getUserByUserId(userId);
			FriendInfoDTO friend = FriendInfoDTO.builder()
					.userId(user.getUserId())
					.nickname(user.getNickname())
					.bio(user.getBio())
					.name(user.getName())
					.gender(user.getGender())
					.birth(user.getBirth())
					.profilePicture(user.getProfilePicture())
					.schoolName("")  // TODO 학교이름 추가
					.build();

			// 찾은 친구 리스트를 넘겨주기
			request.setAttribute("friend", friend);
			url = "views/friendCard.jsp";
		} catch (SQLException e) {
			response.setStatus(400);
			request.setAttribute("errorMsg", "친구 정보 요청 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
