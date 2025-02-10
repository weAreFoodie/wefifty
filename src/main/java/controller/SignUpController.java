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

		
		if (command == null || !command.equals("signup")) {
		    response.sendRedirect("error.jsp"); // 또는 적절한 에러 페이지로 이동
		    return;
		}
		
		ActionFactory af = ActionFactory.getInstance();
		
		Action action = af.getSignUpAction(command);
		action.execute(request, response);
	}

}
