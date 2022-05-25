package com.project2.movieproject.main;

import java.util.ArrayList;

import com.project2.movieproject.command.BuyVO;
import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.RentVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.adminVO;

public interface MainService {
	
	public ArrayList<MovieVO> getMainBanner();
	public ArrayList<MovieVO> getMainRank(String genre);
	public ArrayList<MovieVO> getMainRankList();
	public ArrayList<MovieVO> getMainRentRank(String genre);
	public ArrayList<MovieVO> getMainRentRankList();
	
	public ArrayList<adminVO> getMainNotices();
	
	public MovieVO getMovie(String movie_koficCd);
	public int searchBuy(BuyVO buyVO);
	public int getBuy(BuyVO buyVO);
	public int searchRent(RentVO rentVO);
	public int getRent(RentVO rentVO);
}
