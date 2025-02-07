package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.UserSchoolDTO;
import util.DBUtil;

public class UserSchoolDAO {
	// 회원이 속한 학교들 정보 받아오기
	public static ArrayList<UserSchoolDTO> findUserSchoolByUserId(int userId) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserSchoolDTO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM user_school WHERE user_id=?");
			
			pstmt.setInt(1, userId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<UserSchoolDTO>();
			
			while(rset.next()) {
				list.add(UserSchoolDTO.builder()
						.userSchoolId(rset.getInt("user_school_id"))
						.userId(rset.getInt("user_id"))
						.schoolName(rset.getString("school_name"))
						.gradYear(rset.getInt("grad_year"))
						.schoolType(rset.getString("school_type").charAt(0)).build());
			}
			
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		return list;
	}
	
	// 회원의 학교 정보 추가하기
	public static boolean addUserSchool(UserSchoolDTO school) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO user_school (user_id, school_name, grad_year, school_type) VALUES (?,?,?,?)");
			
			pstmt.setInt(1, school.getUserId());
			pstmt.setString(2, school.getSchoolName());
			pstmt.setInt(3, school.getGradYear());
			pstmt.setString(4, String.valueOf(school.getSchoolType()));
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		return false;
		
	}
	// 회원의 학교 정보 수정하기
	public static boolean updateUserSchool(UserSchoolDTO school) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("UPDATE user_school SET user_id=?, school_name=?, grad_year=?, school_type=? WHERE user_school_id=?");
			
			pstmt.setInt(1, school.getUserId());
	        pstmt.setString(2, school.getSchoolName());
	        pstmt.setInt(3, school.getGradYear());
	        pstmt.setString(4, String.valueOf(school.getSchoolType())); 
	        pstmt.setInt(5, school.getUserSchoolId()); 
	        
	        if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		return false;
	}
	
	// 회원의 학교 정보 삭제하기
	public static boolean deleteUserSchool(int userSchoolId) throws SQLException{
		Connection conn = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM user_school WHERE user_school_id = ?");
	        
			 pstmt.setInt(1, userSchoolId);
	        
			if(pstmt.executeUpdate() != 0) {
				result = true;
			}
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		return result;	
	}
}
