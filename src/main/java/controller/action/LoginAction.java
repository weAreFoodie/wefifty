package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class LoginAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginAction() 실행");
		
		String url = "error.jsp";
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		try {
			UserDTO user = UserDAO.getUserByEmail(request.getParameter("email"));
			
			// 로그인 성공
			if(user != null && BCrypt.checkpw(pwd, user.getPwd())) {
				HttpSession session = request.getSession();
				session.setAttribute("userIdKey", user.getUserId()); // 세션에 userId저장
				
				response.sendRedirect("updateProfile.jsp"); // 홈 페이지로 이동
			} else {
				request.setAttribute("errorMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
            request.setAttribute("errorMsg", "로그인 처리 중 오류가 발생했습니다.");
            request.getRequestDispatcher("login.html").forward(request, response);
		}
	}
}
