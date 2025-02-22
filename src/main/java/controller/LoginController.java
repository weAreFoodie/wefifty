package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.action.Action;
import controller.action.UpdateProfileAction;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("loginController" + command);
		
		
		if (command == null) {
		    command = "login";
		}
		
		ActionFactory af = ActionFactory.getInstance();
		
		Action action = af.getLoginAction(command);
		action.execute(request, response);
	}

}
