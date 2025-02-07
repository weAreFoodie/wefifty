package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.FriendDTO;
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
	public static ArrayList<FriendRequstDTO> findFriendRequestsBySenderId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FriendRequstDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("select * from friend_request where sender_id=?");
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while(rs.next()) {
				requestList.add( FriendRequstDTO.builder()
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
	
	public static ArrayList<FriendRequstDTO> findFriendRequestsByReceiverId(int userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FriendRequstDTO> requestList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			pstmt = conn.prepareStatement("select * from friend_request where receiver_id=?");
			pstmt.setInt(1, userId);
			
			rs = pstmt.executeQuery();
			
			requestList = new ArrayList<>();
			while(rs.next()) {
				requestList.add( FriendRequstDTO.builder()
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
