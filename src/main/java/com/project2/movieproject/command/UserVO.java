package com.project2.movieproject.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	
	/*
	 * 	1. 스프링부트 JPA라이브러리를 사용하면 기본타입은 null을 가질수 없기 때문에 값에 맵핑이 안됩니다.
	   그래서 wrapper형으로 반드시 선언합니다.
	 * 
	 *  2. 유효성 검사에 필요한 멤버변수에 어노테이션 설정
	 *  규제 @NotBlank > @NotEmpty > @NotNull
	 *  
	 *  - @NotNull : null을 허용하지 않는다 (String, Long 등등에 적용가능)
	 *  - @NotBlank : null, "", " " 허용하지 않는다. (String에 적용)
	 *  - @NotEmpty : null, "" 허용하지 않는다. (String, Array등등에 적용가능)
	 *  - @Pattern : 정규표현 형식에 문자열로 정의 할수 있다( String에 적용가능 )
	 *  - @Email : 이메일 형식만 허용
	 */
	
	private Integer user_key;
	private LocalDateTime user_date;
	
	@NotBlank(message = "아이디를 입력해주세요")
	@Size(min = 4, message = "아이디를 4자 이상으로 입력해주세요.")
	private String user_id;
	
	@NotNull(message = "성별을 체크해주세요")
	private Integer user_gender;
	
	@NotNull(message = "생년월일을 체크해주세요")
	private String user_birth;
	
	@NotBlank(message ="이메일을 입력해주세요")
	@Email
	private String user_email;
	private boolean user_auth;
	private boolean user_admin;
	
	@NotBlank(message = "이름을 입력해주세요")
	@Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
	@Pattern(regexp = "^[가-힣]*$", message ="한글 정자로 작성해주세요.")
	private String user_name;
	
	@NotBlank(message = "번호를 입력해주세요")
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message ="000-1111-2222 형식입니다")
	private String user_phone;
	
	@NotBlank(message ="비밀번호를 입력해주세요")
	@Size(min = 4, message = "패스워드를 4자 이상으로 입력해주세요.")
	private String user_password;
	
	private String user_newemail;
	private String user_newphone;
	
	private String user_newpassword;
	private String user_newbirth;
	private String user_delete_check;
	
	private Integer year;
	private Integer month;
	private Integer count;
	
	private String movie_koficcd;
	private String movie_nm;
	private String movie_iurl;
	private String rent_startdate;
	private String rent_enddate;
}
