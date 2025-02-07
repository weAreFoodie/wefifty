package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.UserDTO;
import util.DBUtil;

public class UserDAO {
	// 회원 정보 가져오기
	public static UserDTO getUserByUserId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn =DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user WHERE user_id = ?");
			pstmt.setInt(1, userId);
			
			// 가져온 유저 객체 생성
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return new UserDTO(
	                    rs.getInt("user_id"),
	                    rs.getString("email"),
	                    rs.getString("pwd"),
	                    rs.getString("nickname"),
	                    rs.getString("bio"),
	                    rs.getString("name"),
	                    rs.getString("gender").charAt(0),  // CHAR(1) 타입 변환
	                    rs.getString("phone"),
	                    rs.getDate("birth").toLocalDate(), // java.sql.Date → LocalDate 변환
	                    rs.getString("profile_picture"),
	                    rs.getInt("point")
	                );
	            }
	        }
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return null;
	}
	
	// 회원 추가
	public static int addUser(UserDTO user) throws SQLException {
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
				// db에 저장된 결과 값 가져오기
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					return rs.getInt(1); // 생성된 userId 반환
				}
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return -1;
	}
	
	// 회원 정보 수정
	public static boolean updateUser(UserDTO user) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("UPDATE user SET pwd = ?, nickname = ?, bio = ?, "
					+ "name = ?, gender = ?, phone = ?, birth = ?, "
					+ "profile_picture = ? WHERE user_id = ?");
			
	        pstmt.setString(1, user.getPwd());
	        pstmt.setString(2, user.getNickname());
	        pstmt.setString(3, user.getBio());
	        pstmt.setString(4, user.getName());
	        pstmt.setString(5, String.valueOf(user.getGender()));  // char → String 변환
	        pstmt.setString(6, user.getPhone());
	        pstmt.setDate(7, Date.valueOf(user.getBirth()));  // LocalDate → java.sql.Date 변환
	        pstmt.setString(8, user.getProfilePicture());
	        pstmt.setInt(9, user.getUserId()); // WHERE 조건
			
	        if (pstmt.executeUpdate() != 0) {
	        	return true;
	        }
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
	
	// 회원 정보 삭제
	public static boolean deleteeUser(UserDTO user) throws SQLException  {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM user WHERE user_id = ?");
			
			pstmt.setInt(1, user.getUserId());
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		
		
		return false;
	}
	

}
