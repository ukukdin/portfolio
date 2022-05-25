package com.project2.movieproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project2.movieproject.command.BuyVO;
import com.project2.movieproject.command.CommentVO;
import com.project2.movieproject.command.MovieLikeVO;
import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.RentVO;
import com.project2.movieproject.command.StarRateVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.comment.CommentService;

import com.project2.movieproject.main.MainService;
import com.project2.movieproject.movieDetail.MovieService;



@Controller
@SessionAttributes("vo")
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	@Qualifier("movieService")
	private MovieService movieService;
	
	@Autowired
	@Qualifier("mainService")
	private MainService mainService;

	@GetMapping("/movieComment")
	public void movieComment() {
		
	}
	
	@PostMapping("/user_logout")
    public String user_logout(@ModelAttribute("vo") UserVO vo, SessionStatus status, RedirectAttributes RA) {
       status.setComplete();
       RA.addFlashAttribute("msg", "로그아웃 되었습니다.");
       return "redirect:/main";
    }
	
	@GetMapping("/movieDetail")
	public String movieDetail(@RequestParam("movie_koficCd") String movie_koficCd, 
							  Model model,
							  @ModelAttribute("vo") UserVO sessionvo) {
		
		
		//movie select
		MovieVO movieVo = movieService.getDetail(movie_koficCd);
		
		//유튜브 주소
		String vUrl = movieVo.getMovie_vUrl();
		String url = "https://youtube.com/embed/" + vUrl.substring(32,43);
		
		//코멘트 주소
		String commentUrl = "/comment/commentList?movie_koficCd=" + movie_koficCd;
		model.addAttribute("commentUrl", commentUrl);
		
		//별점
		float starRate = 0;
		if( movieService.getStarRate(movie_koficCd) == null) {
			starRate = 0;
		} else {
			starRate = movieService.getStarRate(movie_koficCd);
		}		
		model.addAttribute("starRate", starRate);
		//리뷰 등록
		model.addAttribute("url", url);
		model.addAttribute("movieVo", movieVo);
		
		//로그인 여부 체크
		int checkLogin;
		
		if (sessionvo.getUser_id() == null) {
			checkLogin = 0;
		} else {
			checkLogin = 1;
		}
		model.addAttribute("checkLogin", checkLogin);
		
		//좋아요 여부 체크
		MovieLikeVO movieLikeVO = new MovieLikeVO();
		movieLikeVO.setMovie_koficCd(movie_koficCd);
		movieLikeVO.setUser_id(sessionvo.getUser_id());
		
		//좋아요 추가/제거
		int movieLike;
		if(movieService.getMovieLike(movieLikeVO) == null) {
			movieLike = 0;
		} else {
			movieLike = 1;
		}
		model.addAttribute("movieLike", movieLike);
		
		return "movie/movieDetail";
		
	}
	
	@GetMapping("/movieList")
	public String movieList(@RequestParam("nation") String nation,
							Model model) {
		
		String genre;
		if(nation.equals("ko")) {
			genre = "action";
			ArrayList<MovieVO> actionList = movieService.getList_ko(genre);
			model.addAttribute("actionList", actionList);
			
			genre = "thriller";
			ArrayList<MovieVO> thrillerList = movieService.getList_ko("thriller");
			model.addAttribute("thrillerList", thrillerList);
			
			genre = "drama";
			ArrayList<MovieVO> dramaList = movieService.getList_ko("drama");
			model.addAttribute("dramaList", dramaList);
			
		} else if(nation.equals("out")){
			
			genre = "action";
			ArrayList<MovieVO> actionList = movieService.getList_out("action");
			model.addAttribute("actionList", actionList);
			
			genre = "thriller";
			ArrayList<MovieVO> thrillerList = movieService.getList_out("thriller");
			model.addAttribute("thrillerList", thrillerList);
			
			genre = "drama";
			ArrayList<MovieVO> dramaList = movieService.getList_out("drama");
			model.addAttribute("dramaList", dramaList);
			
		}
		
		
		return "movie/movieList";
		
	}
	
	@PostMapping("/addStarRate")
	public String addStarRate(@RequestParam ("movie_koficCd") String movie_koficCd,
							  @RequestParam ("rating") int rating,
							  RedirectAttributes RA,
							  Model model
							  ) {
		
		//별점 등록
		StarRateVO starRateVo = new StarRateVO();
		
		starRateVo.setMovie_koficCd(movie_koficCd);
		starRateVo.setUser_key(1);
		starRateVo.setSr_rate(rating);
		
		movieService.addStarRate(starRateVo);
		
		RA.addAttribute("movie_koficCd", movie_koficCd);
		
		return "redirect:/movie/movieDetail";
	}
	
	@PostMapping("/addMovieLike")
	public String addMovieLike(@RequestParam ("movie_koficCd") String movie_koficCd,
							@ModelAttribute("vo") UserVO sessionvo,
							RedirectAttributes RA) {
		
		MovieLikeVO movieLikeVO = new MovieLikeVO();
		movieLikeVO.setMovie_koficCd(movie_koficCd);
		
		String movieLikeMsg;
		if(sessionvo.getUser_id() == null) {
			movieLikeMsg = "로그인이 필요합니다";
			RA.addFlashAttribute("movieLikeMsg", movieLikeMsg);
			return "redirect:/user/userLogin";
		} else {
			movieLikeVO.setUser_id(sessionvo.getUser_id());
			movieService.addMovieLike(movieLikeVO);
			
			movieLikeMsg = "관심 영화를 등록했습니다";
			RA.addFlashAttribute("movieLikeMsg", movieLikeMsg);
			
			RA.addAttribute("movie_koficCd", movie_koficCd);
			
			return "redirect:/movie/movieDetail";
		}
	}
	
	@PostMapping("/removeMovieLike")
	public String removeMovieLike(@RequestParam ("movie_koficCd") String movie_koficCd,
								@ModelAttribute("vo") UserVO sessionvo,
								RedirectAttributes RA) {
		
		MovieLikeVO movieLikeVO = new MovieLikeVO();
		movieLikeVO.setMovie_koficCd(movie_koficCd);
		movieLikeVO.setUser_id(sessionvo.getUser_id());
		movieService.removeMovieLike(movieLikeVO);
		
		RA.addAttribute("movie_koficCd", movie_koficCd);
			
		return "redirect:/movie/movieDetail";
	}

	@GetMapping("/movieBuy")
	public String movieBuy(@RequestParam("movie_koficCd") String movie_koficCd, 
			  				Model model,
			  				@ModelAttribute("vo") UserVO sessionvo,
			  				RedirectAttributes RA) {
		MovieVO movieVO = mainService.getMovie(movie_koficCd);
		
		model.addAttribute("movieVO", movieVO);
		
		if(sessionvo.getUser_id() != null) {
			model.addAttribute("sessionvo", sessionvo);
			return "movie/movieBuy";
		} else {
			RA.addFlashAttribute("msg", "로그인 후 이용해주세요" );
			return "redirect:/movie/movieDetail?movie_koficCd="+movie_koficCd;
		}
	}
	
	@PostMapping("/moviePurchase")
	public String moviePurchase(@RequestParam("movie_koficCd") String movie_koficCd,
								BuyVO buyVO,
								RedirectAttributes RA) {
		
		int cnt = mainService.searchBuy(buyVO);
		if(cnt == 0) {
			int result = mainService.getBuy(buyVO);
			
			if(result == 1) { //성공
				RA.addFlashAttribute("msg", "정상 구매되었습니다" );
			} else { //실패
				RA.addFlashAttribute("msg", "구매실패, 관리자에게 문의하세요");
			}
		} else {
			RA.addFlashAttribute("msg", "이미 구매하신 상품입니다");
		}
		
		return "redirect:/movie/movieDetail?movie_koficCd="+movie_koficCd;
	}
	
	@GetMapping("/movieRent")
	public String movieRent(@RequestParam("movie_koficCd") String movie_koficCd, 
			  				Model model,
			  				@ModelAttribute("vo") UserVO sessionvo,
			  				RedirectAttributes RA) {
		MovieVO movieVO = mainService.getMovie(movie_koficCd);
		
		model.addAttribute("movieVO", movieVO);
		
		if(sessionvo.getUser_id() != null) {
			model.addAttribute("sessionvo", sessionvo);
			return "movie/movieRent";
		} else {
			RA.addFlashAttribute("msg", "로그인 후 이용해주세요" );
			return "redirect:/movie/movieDetail?movie_koficCd="+movie_koficCd;
		}
	}	
	
	@PostMapping("/movieRental")
	public String movieRental(@RequestParam("movie_koficCd") String movie_koficCd,
								RentVO rentVO,
								RedirectAttributes RA) {
		
		int cnt = mainService.searchRent(rentVO);
		if(cnt == 0) {
			int result = mainService.getRent(rentVO);
			
			if(result == 1) { //성공
				RA.addFlashAttribute("msg", "정상 구매되었습니다" );
			} else { //실패
				RA.addFlashAttribute("msg", "구매실패, 관리자에게 문의하세요");
			}
		} else {
			RA.addFlashAttribute("msg", "이미 구매하신 상품입니다");
		}
		
		return "redirect:/movie/movieDetail?movie_koficCd="+movie_koficCd;
	}
}
