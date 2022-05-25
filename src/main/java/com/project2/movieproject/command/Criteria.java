package com.project2.movieproject.command;

import lombok.Data;

@Data
public class Criteria {

	private int page;
	private int amount;
	
	private String searchType;//제목
	private String searchName;//내용
	
	public int amount() {
		return (this.page-1)* amount;
	}
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int page, int amount) {
		super();
		this.page=page;
		this.amount=amount;
	}
}
