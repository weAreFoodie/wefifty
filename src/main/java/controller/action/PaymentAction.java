package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.PaymentAPI;

public class PaymentAction implements Action {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션에 저장된 userId 가져오기
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userIdKey");
		
        int amount = Integer.parseInt(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // 결제 API 호출
            boolean paymentSuccess = PaymentAPI.processPayment(userId, amount, paymentMethod);

            if (paymentSuccess) {
                // 결제가 성공하면 PointChargingAction 실행
            	out.write("{ \"success\": true, \"message\": \"결제가 성공적으로 완료되었습니다.\", \"amount\": " + amount + " }");
            } else {
                out.write("{ \"success\": false, \"message\": \"결제 실패. 다시 시도해주세요.\" }");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{ \"success\": false, \"message\": \"결제 처리 중 오류가 발생했습니다.\" }");
        } finally {
            out.flush();
            out.close();
        }
	}
}
