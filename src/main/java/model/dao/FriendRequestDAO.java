package model.dao;

import java.util.List;

import model.dto.FriendRequstDTO;
import model.dto.UserDTO;

public class FriendRequestDAO {
	// 친구 요청 정보 추가하기
	void addFriendRequest(FriendRequstDTO request) {
		
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
