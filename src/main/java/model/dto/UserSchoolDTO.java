package model.dto;

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
public class UserSchoolDTO {
	private int userSchoolId;
	private int userId;
	private String schoolName;
	private int gradYear;
	private char schoolType;
}
