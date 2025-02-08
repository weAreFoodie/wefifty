package test;

import controller.action.FriendRecommendationAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/RecommendJSPTest")
public class RecommendJSPTest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션 생성 및 테스트용 userId 설정
        HttpSession session = request.getSession();
        session.setAttribute("userIdkey", 1); // 테스트용 userId 설정 (DB에 존재하는 유효한 ID여야 함)

        // FriendRecommendationAction 실행
        FriendRecommendationAction action = new FriendRecommendationAction();
        action.execute(request, response);
    }
}
