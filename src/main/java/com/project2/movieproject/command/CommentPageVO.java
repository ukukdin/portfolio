package com.project2.movieproject.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.project2.movieproject.util.CommentCriteria;

import lombok.Data;

@Data
public class CommentPageVO {
	
	private int commentStart;
	private int commentEnd;
	private boolean commentPrev;
	private boolean commentNext;
	
	private int page;
	private int amount;
	private int total;
	
	private int realEnd;
	private CommentCriteria commentCri;
	
	private List<Integer> pageList;
	
	public CommentPageVO(CommentCriteria commentCri, int total) {
		this.page = commentCri.getPage();
		this.amount = commentCri.getAmount();
		this.total = total;
		this.commentCri = commentCri;
		
		this.commentEnd = (int)Math.ceil(this.page / 10.0) * 10;
		
		this.commentStart = this.commentEnd - 10 + 1;
		
		this.realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		if(this.commentEnd > this.realEnd) {
			this.commentEnd = this.realEnd;
		}
		
		this.commentPrev = this.commentStart > 1;
		
		this.commentNext = this.commentStart > this.commentEnd;
		
		this.pageList = IntStream.rangeClosed(this.commentStart, this.commentEnd)
								 .boxed()
								 .collect(Collectors.toList());
	}

}
