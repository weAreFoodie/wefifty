
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.FriendInfoDTO;
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

  // (학교이름, 졸업년도), 범위값, 유저 아이디 로 해당 범위에 있는 회원의 특정 user 정보 받아오기
	public static ArrayList<FriendInfoDTO> findFriendsBySchoolAndGradYear(ArrayList<UserSchoolSummaryDTO> schoolSummaryList, int gap, int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<FriendInfoDTO> list = new ArrayList<>();
        
        try {
            conn = DBUtil.getConnection();
            
            // SQL 쿼리 동적 생성하기
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT u.user_id, u.nickname, u.bio, u.name, u.gender, u.birth, u.profile_picture, friend_data.year_diff, friend_data.school_name \n");
            sql.append("FROM user u \n");
            sql.append("JOIN (\n");

            for (int i = 0; i < schoolSummaryList.size(); i++) {
                if (i > 0) sql.append(" UNION \n"); // 여러 개의 (학교이름, 졸업년도) 조건을 UNION으로 합치기
                
                sql.append("SELECT us.user_id, ABS(CAST(us.grad_year AS SIGNED) - ?) AS year_diff, us.school_name \n");
                sql.append("FROM user_school us \n");
                sql.append("WHERE us.school_name = ? \n");
                sql.append("AND us.grad_year BETWEEN ? AND ? \n");
                sql.append("AND us.user_id != ? \n"); 
            }

            sql.append(") AS friend_data \n");
            sql.append("ON u.user_id = friend_data.user_id \n");
            sql.append("ORDER BY friend_data.year_diff ASC;"); 
            
            pstmt = conn.prepareStatement(sql.toString());

            // 동적으로 바인딩할 파라미터 설정하기
            int paramIndex = 1;
            for (UserSchoolSummaryDTO school : schoolSummaryList) {
                pstmt.setInt(paramIndex++, school.getGradYear()); // grad_year 비교용
                pstmt.setString(paramIndex++, school.getSchoolName()); // school_name 조건
                pstmt.setInt(paramIndex++, school.getGradYear() - gap); // grad_year 범위 시작
                pstmt.setInt(paramIndex++, school.getGradYear() + gap); // grad_year 범위 끝
                pstmt.setInt(paramIndex++, userId); // 자기 자신 제외
            }

            rset = pstmt.executeQuery();

            // 결과 리스트에 추가하기
            while (rset.next()) {
                FriendInfoDTO friend = new FriendInfoDTO(
                        rset.getInt("user_id"),
                        rset.getString("nickname"),
                        rset.getString("bio"),
                        rset.getString("name"),
                        rset.getString("gender").charAt(0),
                        rset.getDate("birth").toLocalDate(),
                        rset.getString("profile_picture"),
                        rset.getString("school_name")
                );
                list.add(friend);
            }
        } finally {
            DBUtil.close(conn, pstmt, rset);
        }

        return list;
    }

}
