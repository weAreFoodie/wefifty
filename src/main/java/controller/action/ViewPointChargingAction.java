package controller.action;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.PaymentAPI;

public class ViewPointChargingAction implements Action {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "views/pointCharging.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
