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
public class UserDTO {
	private int userId;
	private String email;
	private String pwd;
	private String nickname;
	private String bio;
	private String name;
	private char gender;
	private String phone;
	private LocalDate birth;
	private String profilePicture;
	private int point;
}
