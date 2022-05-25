package com.project2.movieproject.main;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.movieproject.command.BuyVO;
import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.RentVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.adminVO;

@Service("mainService")
public class MainServiceImpl implements MainService {

	@Autowired
	private MainMapper mainMapper;

	@Override
	public ArrayList<MovieVO> getMainBanner() {
		return mainMapper.getMainBanner();
	}

	@Override
	public ArrayList<MovieVO> getMainRank(String genre) {
		return mainMapper.getMainRank(genre);
	}

	@Override
	public ArrayList<MovieVO> getMainRankList() {
		return mainMapper.getMainRankList();
	}
	
	@Override
	public ArrayList<MovieVO> getMainRentRank(String genre) {
		return mainMapper.getMainRentRank(genre);
	}

	@Override
	public ArrayList<MovieVO> getMainRentRankList() {
		return mainMapper.getMainRentRankList();
	}
	
	@Override
	public ArrayList<adminVO> getMainNotices() {
		return mainMapper.getMainNotices();
	}

	@Override
	public MovieVO getMovie(String movie_koficCd) {
		
		return mainMapper.getMovie(movie_koficCd);
	}

	@Override
	public int searchBuy(BuyVO buyVO) {
		return mainMapper.searchBuy(buyVO);
	}

	@Override
	public int getBuy(BuyVO buyVO) {
		return mainMapper.getBuy(buyVO);
	}

	@Override
	public int searchRent(RentVO rentVO) {
		return mainMapper.searchRent(rentVO);
	}

	@Override
	public int getRent(RentVO rentVO) {
		return mainMapper.getRent(rentVO);
	}
}
