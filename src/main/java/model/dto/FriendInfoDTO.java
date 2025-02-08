package model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FriendInfoDTO {
	private int userId;
	private String nickname;
	private String bio;
	private String name;
	private char gender;
	private LocalDate birth;
	private String profilePicture;
	private String schoolName;  // 추천 로직에서 추천의 근거가 된 학교이름(회원의 학교이름과 같은 친구의 학교이름)
}
