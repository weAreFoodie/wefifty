package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.dto.FriendRequstDTO;
import model.dto.UserDTO;
import util.DBUtil;

public class FriendRequestDAO {
	// 친구 요청 정보 추가하기
	public static boolean addFriendRequest(FriendRequstDTO friendRequestDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("insert (sender_id, receiver_id, status) into friend_request values(null, ?, ?, ?)");
			pstmt.setInt(1, friendRequestDTO.getSenderId());
			pstmt.setInt(2, friendRequestDTO.getReceiverId());
			pstmt.setString(3, String.valueOf(friendRequestDTO.getStatus()));
		
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
			
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		return false;
	}
	
	// 친구 요청 정보 변경하기(수락, 거절)
	void updateFriendRequest(int requestId) {
		
	}
	
	// 해당 회원 친구 요청 목록 가져오기
	List<FriendRequstDTO> findFriendRequestsByUserId(int userId) {
		return null;
	}
	
	// 같은 학교, 졸업 년도(gap) 친구 목록 받아오기
	List<UserDTO> findFriendsBySchoolAndGradYear(String schoolName, int gradYear, int gap){
		return null;
	}
	
	
}
