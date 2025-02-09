package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;

public class PointChargingAction implements Action {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 // 세션에 저장된 userId 가져오기 
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userIdkey");
        
        // FIXME: 세션 구현 전 임시 코드
        userId = 1;
        
        int amount = Integer.parseInt(request.getParameter("amount"));
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            boolean success = UserDAO.updatePoint(userId, amount);
            
            if (success) {
            	request.getSession().setAttribute("chargedAmount", amount); // 세션에 충전 금액 저장
                out.write("{ \"success\": true, \"message\": \"포인트 충전이 완료되었습니다.\", \"amount\": " + amount + " }");
            } else {
                out.write("{ \"success\": false, \"message\": \"포인트 충전에 실패했습니다.\" }");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.write("{ \"success\": false, \"message\": \"데이터베이스 오류가 발생했습니다.\" }");
        } finally {
            out.flush();
            out.close();
        }
	}
}
