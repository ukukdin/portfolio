package com.project2.movieproject.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class SPVO {

	// 시작 페이지
	private int startPage;
	// 끝 페이지
	private int endPage;
	// 이전 페이지, 다음 페이지 존재 유무
	private boolean prev;
	private boolean next;
	
	private int amount;
	private int pageNum;
	// 전체 게시물 수
	private int total;
	private int realEnd;
	// 현재 페이지, 페이지당 게시물 표시수 정보
	private SearchCriteria searchCriteria;
	
	private List<Integer> pageNumList;
	
	public SPVO(SearchCriteria searchCriteria, int total) {
		this.pageNum = searchCriteria.getPageNum();
		this.amount = searchCriteria.getAmount();
		this.total = total;
		this.searchCriteria = searchCriteria;
		
		// 마지막 페이지
		this.endPage = (int) Math.ceil(searchCriteria.getPageNum() / 10.0) * 10;
		// 시작 페이지
		this.startPage = this.endPage - 9;
		// 전체 마지막 페이지
		int realEnd = (int) (Math.ceil(total * 1.0 / searchCriteria.getAmount()));
		// 전체 마지막 페이지(realEnd)가 화면에 보이는 마지막 페이지(endPage)보다 작은 경우, 보이는 페이지(endPage)값 조정
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		// 시작 페이지(startPage)값이 1보다 큰 경우 true
		this.prev = this.startPage > 1;
		// 마지막 페이지(endPage)값이 1보다 큰 경우 true
		this.next = realEnd > this.endPage;
		this.pageNumList = IntStream.rangeClosed(this.startPage, this.endPage)
				.boxed()
				.collect(Collectors.toList());
	}


}
