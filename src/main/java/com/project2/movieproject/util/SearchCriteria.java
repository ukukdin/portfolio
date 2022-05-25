package com.project2.movieproject.util;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class SearchCriteria {

	// 현재 페이지
	public int pageNum;
	// 한 페이지당 보일 게시물 개수
	public int amount;
	// 스킵할 게시물 수((pageNum-1) * amount)
	private int skip;
	
	private String searchType; // 검색 유형
	private String searchName; // 검색 내용
	private String[] searchTypeArr; // 검색 유형 배열 변환
	
	public SearchCriteria() {
		this(1, 10);
		this.skip = 0;
	}
	public SearchCriteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}	
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
		this.searchTypeArr = searchType.split("");
	}
	public String getListLink() {
		String path = "";
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path)
										.queryParam("pageNum", this.pageNum)
										.queryParam("amount", this.amount)
										.queryParam("searchType", this.getSearchType())
										.queryParam("searchName", this.getSearchName());
		return builder.toUriString();
	}

}

