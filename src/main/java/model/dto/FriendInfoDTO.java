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
}
