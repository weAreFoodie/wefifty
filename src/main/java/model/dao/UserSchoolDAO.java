package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.FriendInfoListDTO;
import model.dto.UserSchoolDTO;
import model.dto.UserSchoolSummaryDTO;
import util.DBUtil;

public class UserSchoolDAO {
	// 회원이 속한 학교들의 이름과 졸업년도 받아오기
	public static ArrayList<UserSchoolSummaryDTO> findUserSchoolSummaryByUserId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserSchoolSummaryDTO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT school_name, grad_year FROM user_school WHERE user_id = ?");
			
			pstmt.setInt(1, userId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<UserSchoolSummaryDTO>();
			
			while(rset.next()) {
				list.add(UserSchoolSummaryDTO.builder()
						.schoolName(rset.getString("school_name"))
						.gradYear(rset.getInt("grad_year")).build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return list;
	}
	
	// 회원이 속한 학교들 정보 받아오기
	public static ArrayList<UserSchoolDTO> findUserSchoolByUserId(int userId) throws SQLException {
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
	public static boolean addUserSchool(UserSchoolDTO school) throws SQLException {
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
	public static boolean updateUserSchool(UserSchoolDTO school) throws SQLException {
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
	public static boolean deleteUserSchool(int userSchoolId) throws SQLException {
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
	
	// 학교이름, 졸업년도, 범위값으로 해당 범위에 있는 회원의 user_id 받아오기
	public static ArrayList<FriendInfoListDTO> findFriendsBySchoolAndGradYear(ArrayList<UserSchoolSummaryDTO> schoolSummaryList, int gap, int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FriendInfoListDTO> friendList = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection(); // DB 연결

	        // 동적으로 SQL 쿼리 생성
	        StringBuilder queryBuilder = new StringBuilder();
	        queryBuilder.append("SELECT DISTINCT u.user_id, u.nickname, u.bio, u.name, u.gender, u.birth, u.profile_picture, ")
	                    .append("ABS(us.grad_year - ?) AS grad_diff ")
	                    .append("FROM user u ")
	                    .append("JOIN user_school us ON u.user_id = us.user_id ")
	                    .append("WHERE u.user_id != ? AND (");

	        List<Object> params = new ArrayList<>();
	        params.add(userId); // 자신의 ID를 제외하기 위해 추가

	        for (int i = 0; i < schoolSummaryList.size(); i++) {
	            if (i > 0) queryBuilder.append(" UNION ");
	            queryBuilder.append("SELECT u.user_id, u.nickname, u.bio, u.name, u.gender, u.birth, u.profile_picture, ")
	                        .append("ABS(us.grad_year - ?) AS grad_diff ")
	                        .append("FROM user u ")
	                        .append("JOIN user_school us ON u.user_id = us.user_id ")
	                        .append("WHERE u.user_id != ? AND us.school_name = ? AND us.grad_year BETWEEN ? AND ? ");
	            
	            // 각 학업 정보에 대해 조건 추가
	            params.add(schoolSummaryList.get(i).getGradYear()); // grad_diff 계산용
	            params.add(userId); // 자신의 ID 제외
	            params.add(schoolSummaryList.get(i).getSchoolName()); // 학교 이름
	            params.add(schoolSummaryList.get(i).getGradYear() - gap); // 최소 졸업 연도
	            params.add(schoolSummaryList.get(i).getGradYear() + gap); // 최대 졸업 연도
	        }

	        queryBuilder.append(") ORDER BY grad_diff ASC");

	        // SQL 실행
	        pstmt = conn.prepareStatement(queryBuilder.toString());

	        // 파라미터 세팅
	        for (int i = 0; i < params.size(); i++) {
	            if (params.get(i) instanceof Integer) {
	                pstmt.setInt(i + 1, (Integer) params.get(i));
	            } else if (params.get(i) instanceof String) {
	                pstmt.setString(i + 1, (String) params.get(i));
	            }
	        }

	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            FriendInfoListDTO friend = new FriendInfoListDTO(
	                rset.getInt("user_id"),
	                rset.getString("nickname"),
	                rset.getString("bio"),
	                rset.getString("name"),
	                rset.getString("gender").charAt(0),
	                rset.getDate("birth").toLocalDate(),
	                rset.getString("profile_picture")
	            );
	            friendList.add(friend);
	        }
		} finally {
			DBUtil.close(conn, pstmt, rset);
		}
		
		return list;
	}
}
