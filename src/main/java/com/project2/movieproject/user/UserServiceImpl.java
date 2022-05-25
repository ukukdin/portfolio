package com.project2.movieproject.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.movieproject.command.CommentVO;
import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.qaVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int regist(UserVO vo) {
		
		return userMapper.regist(vo);
	}

	@Override
	public int idCheck(String id) {
		return userMapper.idCheck(id);
	}

	@Override
	public ArrayList<UserVO> userdata(String db_id) {
		return userMapper.userdata(db_id);
	}

	@Override
	public int user_update(UserVO vo) {
		return userMapper.user_update(vo);
	}

	@Override
	public int user_delete(UserVO vo) {
		return userMapper.user_delete(vo);
	}

	@Override
	public ArrayList<UserVO> userlist(Criteria cri) {
		return userMapper.userlist(cri);
	}

	@Override
	public int qa_regist(qaVO vo) {
		return userMapper.qa_regist(vo);
	}

	@Override
	public ArrayList<qaVO> myqa_read(String user_id) {
		return userMapper.myqa_read(user_id);
	}

	@Override
	public ArrayList<qaVO> qa_read(Integer qa_key) {
		return userMapper.qa_read(qa_key);
	}

	@Override
	public int total(Criteria cri) {
		// TODO Auto-generated method stub
		return userMapper.total(cri);
	}

	@Override
	public ArrayList<qaVO> qa_list() {
		return userMapper.qa_list();
	}

	@Override
	public int qa_update(qaVO vo) {
		return userMapper.qa_update(vo);
	}

	@Override
	public int auth_update(String id) {
		return userMapper.auth_update(id);
	}
	
	/*
	 * @Override public ArrayList<UserVO> userlist1() { // TODO Auto-generated
	 * method stub return userMapper.userlist1(); }
	 */

	@Override
	public int FindId(UserVO vo) {
		return userMapper.FindId(vo);
	}

	@Override
	public String FindyourId(UserVO vo) {
		return userMapper.FindyourId(vo);
	}

	@Override
	public int FindPw(UserVO vo) {
		return userMapper.FindPw(vo);
	}

	@Override
	public int emailCheck(String email) {
		return userMapper.emailCheck(email);
	}

	@Override
	public ArrayList<CommentVO> mycomment(UserVO vo) {
		return userMapper.mycomment(vo);
	}

	@Override
	public ArrayList<UserVO> movie_like(UserVO vo) {
		return userMapper.movie_like(vo);
	}
	
	@Override
	public UserVO getUserCount(String month) {
		return userMapper.getUserCount(month);
	}

	@Override
	public ArrayList<qaVO> qa_table(Criteria cri) {
		// TODO Auto-generated method stub
		return userMapper.qa_table(cri);
	}

	@Override
	public ArrayList<UserVO> user_buy_list(UserVO vo) {
		return userMapper.user_buy_list(vo);
	}

	@Override
	public ArrayList<UserVO> user_rent_list(UserVO vo) {
		return userMapper.user_rent_list(vo);
	}
	
}
