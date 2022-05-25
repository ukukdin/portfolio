package com.project2.movieproject.admin;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.adminVO;

@Mapper
public interface AdminMapper {
	public int movieRegist(MovieVO vo);
	public ArrayList<MovieVO> getMovieList(Criteria cri);
	public int getTotal(Criteria cri);
	public MovieVO getMovieDetail(int movie_koficCd);
	public int movieUpdate(MovieVO movieVO);
	
	
	public int noticeRegist(adminVO vo);//등록
	public ArrayList<adminVO> List(Criteria cri);//목록
	public int total(Criteria cri);//전체게시글수
	public adminVO getcontent(int admin_key);//상세
	public int hit(adminVO adminvo);//조회수
	public int update(adminVO adminvo);//수정
	public int delete(int admin_id);//삭제
	
	
	public ArrayList<MovieVO> searchTable(Criteria cri);//serach 
	public ArrayList<MovieVO> searchlist();
}
