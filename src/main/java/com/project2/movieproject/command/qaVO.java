package com.project2.movieproject.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class qaVO {
/*  qa_key      INT   PRIMARY KEY AUTO_INCREMENT,
   user_key   INT   NOT NULL, ##FK
   qa_title   VARCHAR(20)   NOT NULL,
   qa_content   VARCHAR(5000)   NOT NULL,
   qa_date      TIMESTAMP DEFAULT NOW()*/
	
	private Integer qa_key;
	private String user_id;
	
	@NotEmpty
	private String qa_title;
	@NotEmpty
	private String qa_content;
	
	private LocalDateTime qa_date;
	private String qa_comment;
}
