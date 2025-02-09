package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.FriendDTO;
import model.dto.FriendRequestDTO;
import model.dto.ReceiveFriendDTO;
import model.dto.SendFriendDTO;
import util.DBUtil;

public class FriendRequestDAO {
	// 친구 요청 정보 추가하기
	public static boolean addFriendRequest(FriendRequestDTO friendRequestDTO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);  // 트랜잭션 시작
			
			pstmt1 = conn.prepareStatement("UPDATE user SET point=(point-500) WHERE user_id=?");
			pstmt1.setInt(1, friendRequestDTO.getSenderId());
			int rowsUpdate1 = pstmt1.executeUpdate();
			
			pstmt2 = conn.prepareStatement("INSERT INTO friend_request (sender_id, receiver_id, status) VALUES (?, ?, ?)");
			pstmt2.setInt(1, friendRequestDTO.getSenderId());
			pstmt2.setInt(2, friendRequestDTO.getReceiverId());
			pstmt2.setString(3, String.valueOf(friendRequestDTO.getStatus()));
			int rowsUpdate2 = pstmt2.executeUpdate();
		
			if (rowsUpdate1 != 0 && rowsUpdate2 != 0) {
				conn.commit();
				return true;
			}
			
		} catch(SQLException e) {	
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);  // 커넥션 풀을 사용하기 때문에 autocommit 옵션 true로 복귀시켜햐 함
			DBUtil.close(conn, new PreparedStatement[] {pstmt1, pstmt2});
		}
		
		return false;
	}
	
	// 친구 요청 정보 변경하기(수락, 거절)
	public static boolean updateFriendRequest(int requestId, char status) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		try {
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);  // 트랜잭션
			
			pstmt1 = conn.prepareStatement("update friend_request set status=? where id=?");
			pstmt1.setString(1, String.valueOf(status));
			pstmt1.setInt(2, requestId);
			int rowsUpdate1 = pstmt1.executeUpdate();
			
			if (status == 'r') {  // 거절인 경우
				// sender 찾기
				int senderId = 0;
				pstmt2 = conn.prepareStatement("select sender_id from friend_request where id=?");
				pstmt2.setInt(1, requestId);
				ResultSet rs = pstmt2.executeQuery();
				
				if (rs.next()) {
					senderId = rs.getInt("sender_id");
				}
				
				//포인트 +500
				pstmt3 = conn.prepareStatement("UPDATE user SET point=(point+500) WHERE user_id=?");
				pstmt3.setInt(1, senderId);
				int rowsUpdate3 = pstmt3.executeUpdate();
			
				if (rowsUpdate1 != 0 && rowsUpdate3 != 0) {
					conn.commit();
					return true;
				}
			} else {
				if (rowsUpdate1 != 0) {
					conn.commit();
					return true;
				}
			}
		} catch(SQLException e) {	
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
			DBUtil.close(conn, new PreparedStatement[] {pstmt1, pstmt2});
		}
		
		return false;
	}
	
	// 해당 회원 친구 요청 목록 가져오기
	public static ArrayList<SendFriendDTO> findFriendRequestsBySenderId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SendFriendDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("select user_id, name, profile_picture, status\r\n"
					+ "from (select receiver_id, status from friend_request where sender_id=?) as send join user\r\n"
					+ "where receiver_id = user_id");
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while (rs.next()) {
				requestList.add(SendFriendDTO.builder()
						.userId(rs.getInt("user_id"))
						.name(rs.getString("name"))
						.profilePicture(rs.getString("profile_picture"))
						.status(rs.getString("status").charAt(0))
						.build());
			}
			
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		

		return requestList;
	}
	
	// 요청 대기 중인 목록만 가져오도록
	public static ArrayList<ReceiveFriendDTO> findFriendRequestsByReceiverId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReceiveFriendDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("select user_id, id, name, profile_picture\r\n"
					+ "from (select id, sender_id, status from friend_request where receiver_id=?) as receive join user\r\n"
					+ "where sender_id = user_id and status = 'p'");
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while (rs.next()) {
				requestList.add(ReceiveFriendDTO.builder()
						.userId(rs.getInt("user_id"))
						.requestId(rs.getInt("id"))
						.name(rs.getString("name"))
						.profilePicture(rs.getString("profile_picture"))
						.build());
			}
			
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		
		
		return requestList;
	}
	
	public static boolean deleteFriendRequest(int requestId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("delete from friend_request where id=?");
			pstmt.setInt(1, requestId);
			
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
			
		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		
		return false;
	}
	
	// 친구 목록 가져오기 - 요청 보내고 받은 목록 중 - 내가 보내거나 받은 목록 중 - 수락된 목록
	public static ArrayList<FriendDTO> findFriendListByUserId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FriendDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String q ="select user.user_id, name, profile_picture, gender, phone, email\r\n"
					+ "from\r\n"
					+ "(\r\n"
					+ "	select\r\n"
					+ "		sender_id as user_id\r\n"
					+ "	from\r\n"
					+ "		friend_request\r\n"
					+ "	where\r\n"
					+ "		receiver_id = ?\r\n"
					+ "		and status = 'a'\r\n"
					+ "union\r\n"
					+ "	select\r\n"
					+ "		receiver_id as user_id\r\n"
					+ "	from\r\n"
					+ "		friend_request\r\n"
					+ "	where\r\n"
					+ "		sender_id = ?\r\n"
					+ "		and status = 'a') as friends\r\n"
					+ "join\r\n"
					+ "user\r\n"
					+ "where\r\n"
					+ "	user.user_id = friends.user_id;";
			
			pstmt = conn.prepareStatement(q);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while(rs.next()) {
				requestList.add( FriendDTO.builder()
						.userId(rs.getInt("user_id"))
						.email(rs.getString("email"))
						.name(rs.getString("name"))
						.gender(rs.getString("gender").charAt(0))
						.phone(rs.getString("phone"))
						.profilePicture(rs.getString("profile_picture"))
						.build()
						);
			}
			
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		
		return requestList;
	}
	
}
