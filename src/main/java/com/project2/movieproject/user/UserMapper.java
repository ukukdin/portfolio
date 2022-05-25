package com.project2.movieproject.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project2.movieproject.command.CommentVO;
import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.qaVO;

@Mapper
public interface UserMapper {

	public int regist(UserVO vo); //등록
	public int idCheck(String id);
	public ArrayList<UserVO> userdata(String db_id);
	public int user_update(UserVO vo);
	public int user_delete(UserVO vo);
	public ArrayList<UserVO> userlist(Criteria cri);
	public int qa_regist(qaVO vo);
	public ArrayList<qaVO> myqa_read(String user_id);
	public ArrayList<qaVO> qa_read(Integer qa_key);
	public int total(Criteria cri);//전체회원글수
	public ArrayList<qaVO> qa_list();
	public int qa_update(qaVO vo);
	public int auth_update(String id);
	public int FindId(UserVO vo);
	public String FindyourId(UserVO vo);
	public int FindPw(UserVO vo);
	public int emailCheck(String email);
	public ArrayList<CommentVO> mycomment(UserVO vo);
	public ArrayList<UserVO> movie_like(UserVO vo);
	public UserVO getUserCount(String month);//게시판 데이터 날짜 함수 
	public ArrayList<UserVO> user_buy_list(UserVO vo);
	public ArrayList<qaVO> qa_table(Criteria cri);
	public ArrayList<UserVO> user_rent_list(UserVO vo);
}
