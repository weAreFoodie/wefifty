package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.UserDTO;
import util.DBUtil;

public class UserDAO {
	// 회원 정보 가져오기
	UserDTO getUserByUserId(UserDTO user) throws SQLException {
		return null;
	}
	
	// 회원 추가
	boolean addUser(UserDTO user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("INSERT INTO user (email, pwd, nickname, bio, name, gender, phone, birth, profile_picture, point)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getBio());
			pstmt.setString(5, user.getName());
			pstmt.setString(6, String.valueOf(user.getGender()));  // char 값을 String으로 변환
			pstmt.setString(7, user.getPhone());
			pstmt.setDate(8, Date.valueOf(user.getBirth()));  // LocalDate → java.sql.Date 변환
			pstmt.setString(9, user.getProfilePicture());
			pstmt.setInt(10, user.getPoint());
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}

		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
	
	// 회원 정보 저장
	void updateUser(UserDTO user) {
		
	}
	
	// 회원 정보 삭제
	void deleteeUser(UserDTO user) {
		
	}
	

}
