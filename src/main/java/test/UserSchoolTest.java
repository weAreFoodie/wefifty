package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserSchoolDAO;
import model.dto.FriendInfoDTO;
import model.dto.UserSchoolSummaryDTO;

@WebServlet("/testUserSchool")
public class UserSchoolTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. 요청 파라미터 받기
    	ArrayList<UserSchoolSummaryDTO> schoolSummaryList = new ArrayList<>();
    	schoolSummaryList.add(new UserSchoolSummaryDTO("ABC High School", 2015));
    	schoolSummaryList.add(new UserSchoolSummaryDTO("ABC University", 2020));
        int gap = 2; // ±2년 범위
        int userId = 1; // user_id = 1번 회원이 검색하는 상황

        // 3. DB 조회
        ArrayList<FriendInfoDTO> resultList = new ArrayList<>();
        try {
            resultList = UserSchoolDAO.findFriendsBySchoolAndGradYear(schoolSummaryList, gap, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        // 4. 응답 출력
        for (FriendInfoDTO dto : resultList) {
        	System.out.println(dto.getUserId() + " / " + dto.getSchoolName() + " / " + dto.getName() + " / " + dto.getNickname());
        }
    }
}
