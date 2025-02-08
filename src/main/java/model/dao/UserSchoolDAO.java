package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.dto.UserSchoolDTO;
import util.DBUtil;

public class UserSchoolDAO {
	// 회원이 속한 학교들 정보 받아오기
	List<UserSchoolDTO> findSchoolByUserId(int userId){
		return null;
	}
	
	// 학교 추가
	void addSchool(UserSchoolDTO School) {
		
	}
	// 학교 수정
	void updateUserSchool(UserSchoolDTO school) {
		
	}
	
	// 학교 삭제
	void deleteSchoolById(int userSchoolId) {
		
	}
	
	// 유저 학교 정보 집어넣기
	public static boolean insertUserSchools(int userId, List<UserSchoolDTO> schoolList) {
	    String sql = "INSERT INTO user_school (user_id, school_name, grad_year, school_type) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	    	// 학교들 저장
	        for (UserSchoolDTO school : schoolList) {
	            pstmt.setInt(1, userId);
	            pstmt.setString(2, school.getSchoolName());
	            pstmt.setInt(3, school.getGradYear());
	            pstmt.setString(4, String.valueOf(school.getSchoolType())); // char → String 변환

	            pstmt.addBatch();  // 배치 실행 추가
	        }

	        pstmt.executeBatch();  // 배치 실행
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
