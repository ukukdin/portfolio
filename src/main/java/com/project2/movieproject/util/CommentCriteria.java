package com.project2.movieproject.util;

import lombok.Data;

@Data
public class CommentCriteria {
	
	private int page;
	private int amount;
	private String movie_koficCd;
	
	public CommentCriteria() {
		this.page = 1;
		this.amount = 10;
	}
	
	public CommentCriteria(int page, int amount, String movie_koficCd) {
		super();
		this.page = page;
		this.amount = amount;
		this.movie_koficCd = movie_koficCd;
	}

}
