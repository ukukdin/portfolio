package com.project2.movieproject.comment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.movieproject.command.CommentVO;
import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.util.CommentCriteria;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public int regist(CommentVO vo) {
		
		return commentMapper.regist(vo);
		
	}

	@Override
	public ArrayList<CommentVO> getList(CommentCriteria commentCri) {
		return commentMapper.getList(commentCri);

	}
	@Override
	public int getTotal(String movie_koficCd) {
		return commentMapper.getTotal(movie_koficCd);
	}
	
	

}
