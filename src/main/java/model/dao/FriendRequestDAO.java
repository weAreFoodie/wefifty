package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.FriendDTO;
import model.dto.FriendRequestDTO;
import model.dto.UserDTO;
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
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("update friend_request set status=? where id=?");
			
			pstmt.setInt(1, status);
			pstmt.setInt(2, requestId);
			
			if (pstmt.executeUpdate() != 0) {
				return true;
			}

		} finally {
			DBUtil.close(conn, pstmt);
		}
		
		return false;
	}
	
	// 해당 회원 친구 요청 목록 가져오기
	public static ArrayList<FriendRequestDTO> findFriendRequestsBySenderId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FriendRequestDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("select * from friend_request where sender_id=?");
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while(rs.next()) {
				requestList.add( FriendRequestDTO.builder()
									.id(rs.getInt("id"))
									.senderId(rs.getInt("sender_id"))
									.receiverId(rs.getInt("receiver_id"))
									.status(rs.getString("status").charAt(0))
									.build()
						);
			}
			
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		

		return requestList;
	}
	
	public static ArrayList<FriendRequestDTO> findFriendRequestsByReceiverId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FriendRequestDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("select * from friend_request where receiver_id=?");
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while(rs.next()) {
				requestList.add( FriendRequestDTO.builder()
						.id(rs.getInt("id"))
						.senderId(rs.getInt("sender_id"))
						.receiverId(rs.getInt("receiver_id"))
						.status(rs.getString("status").charAt(0))
						.build()
						);
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
