package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import controller.action.Action;

@WebServlet("/test")
public class Test extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		if(command == null) {
			command = "";   //모든 방명록 보기
		}
		
		ActionFactory af = ActionFactory.getInstance();
	
		Action action = af.getHomeAction(command);  //list, view, updateForm, write, update
		action.execute(request, response);
	}

}
