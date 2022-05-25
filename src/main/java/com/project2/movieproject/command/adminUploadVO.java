package com.project2.movieproject.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class adminUploadVO {

	
	private Integer upload_no;
	private String filename;
	private String filepath;
	private String uuid;
	private LocalDateTime regdate;
	private Integer user_id;
	private String user_admin;
	
	
	
}
