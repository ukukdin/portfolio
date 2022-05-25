package com.project2.movieproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchVO {
	
	private String movie_iUrl;
	private String movie_nm; // 영화명
	private String movie_dirNm; // 감독명
	private String movie_actNm; // 배우명
	private String movie_apiRt; // api 평점
	private String movie_ov; // 영화 소개
	
	// 검색 필터
	private String type; // 검색 타입
	private String keyword; // 검색 내용
}
