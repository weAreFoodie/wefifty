package controller;

import java.io.IOException;

import controller.action.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		if(command == null) {
		
		}
		
		ActionFactory af = ActionFactory.getInstance();
		
		Action action = af.getHomeAction(command);
		action.execute(request, response);
	}

}
