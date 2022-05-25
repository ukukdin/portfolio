package com.project2.movieproject.movieDetail;

import java.util.ArrayList;

import com.project2.movieproject.command.MovieLikeVO;
import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.StarRateVO;

public interface MovieService {

	public MovieVO getDetail(String movie_koficCd);
	public ArrayList<MovieVO> getList_ko(String genre);
	public ArrayList<MovieVO> getList_out(String genre);
	public void addStarRate(StarRateVO starRateVo);
	public Float getStarRate(String movie_koficCd);
	public MovieLikeVO getMovieLike(MovieLikeVO movieLikeVO);
	public void addMovieLike(MovieLikeVO movieLikeVO);
	public void removeMovieLike(MovieLikeVO movieLikeVO);
}
