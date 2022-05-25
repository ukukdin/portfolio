package com.project2.movieproject.comment;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project2.movieproject.command.CommentVO;
import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.util.CommentCriteria;

@Mapper
public interface CommentMapper {
	
	public int regist(CommentVO vo);
	
	public ArrayList<CommentVO> getList(CommentCriteria commentCri);	
	
	public int getTotal(String movie_koficCd);
	

}
