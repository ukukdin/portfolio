package com.project2.movieproject.command;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class adminVO {

/*ADMIN_KEY INT PRIMARY KEY AUTO_INCREMENT,
NOTICES_TITLE VARCHAR(20) NOT NULL,
NOTICES_DATE TIMESTAMP DEFAULT NOW(),
USER_ADMIN BOOLEAN DEFAULT FALSE,
NOTICES_COUNT INT NOT NULL,
NOTICES_CONTENT VARCHAR(200) NOT NULL
 * */
	
	private Integer admin_key;
	private String notices_title;//제목
	
	
	
	private LocalDateTime notices_date;
	
	private String admin_id;
	private boolean admin_user;
	private int notices_count;//조횟수 증가
	private String notices_content;//내용

	private String filename;
	private String FILEPATH;
	private String uuid;
	private String fileDelete;
	private String fileUpdate;
	//공지사항
	
	
	
}
