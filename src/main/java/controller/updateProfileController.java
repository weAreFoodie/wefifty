package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.action.Action;
import controller.action.UpdateProfileAction;

/**
 * Servlet implementation class updateProfileController
 */
@WebServlet("/updateProfile")
public class updateProfileController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		
        Action action = null;
        
        if ("updateProfile".equals(command)) {
            action = new UpdateProfileAction();
        } else {
            System.out.println("null"); // 기본 홈 화면 액션
        }

        action.execute(request, response);
	}

}
