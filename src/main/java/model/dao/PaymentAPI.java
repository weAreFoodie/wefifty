package model.dao;

public class PaymentAPI {
    public static boolean processPayment(int userId, int amount, String paymentMethod) {
        // 실제 결제 API 연동 
        System.out.println("결제 요청: userId=" + userId + ", amount=" + amount + ", paymentMethod=" + paymentMethod);

        // 외부 결제 API와 연동 후 결과 반환 (테스트용으로 성공 처리)
        return true;  
    }
}
