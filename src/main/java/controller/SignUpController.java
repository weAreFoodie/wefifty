package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.action.Action;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("signUpController" + command);

		
		if (command == null) {
			response.setStatus(400);
		    request.setAttribute("errorMsg", "회원가입을 다시 시도해주세요.");
		    return;
		}
		
		ActionFactory af = ActionFactory.getInstance();
		
		Action action = af.getSignUpAction(command);
		action.execute(request, response);
	}

}
