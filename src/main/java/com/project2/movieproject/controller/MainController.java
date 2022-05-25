package com.project2.movieproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.adminVO;
import com.project2.movieproject.main.MainService;

@Controller
@SessionAttributes("vo")
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@ModelAttribute("vo")
	public UserVO setUserVO() {
		System.out.println("***********setUserVO()**********");
		return new UserVO();
	}
	
	
	@GetMapping("main")
	public String main(Model model, @ModelAttribute("vo") UserVO vo) {
		ArrayList<MovieVO> main_banner = mainService.getMainBanner();
		model.addAttribute("main_banner", main_banner);
				
		String genre = "all";
		ArrayList<MovieVO> main_all_all = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_all = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_all", main_all_all);
		model.addAttribute("rent_all_all", rent_all_all);
		
		genre = "act";
		ArrayList<MovieVO> main_all_act = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_act = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_act", main_all_act);
		model.addAttribute("rent_all_act", rent_all_act);
		
		genre = "fan";
		ArrayList<MovieVO> main_all_fan = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_fan = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_fan", main_all_fan);
		model.addAttribute("rent_all_fan", rent_all_fan);
		
		genre = "crime";
		ArrayList<MovieVO> main_all_crime = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_crime = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_crime", main_all_crime);
		model.addAttribute("rent_all_crime", rent_all_crime);
		
		genre = "rom";
		ArrayList<MovieVO> main_all_rom = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_rom = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_rom", main_all_rom);
		model.addAttribute("rent_all_rom", rent_all_rom);
		
		genre = "com";
		ArrayList<MovieVO> main_all_com = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_com = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_com", main_all_com);
		model.addAttribute("rent_all_com", rent_all_com);
		
		genre = "hor";
		ArrayList<MovieVO> main_all_hor = mainService.getMainRank(genre);
		ArrayList<MovieVO> rent_all_hor = mainService.getMainRentRank(genre);
		model.addAttribute("main_all_hor", main_all_hor);
		model.addAttribute("rent_all_hor", rent_all_hor);
		
		ArrayList<MovieVO> main_rankList = mainService.getMainRankList();
		ArrayList<MovieVO> main_rentRankList = mainService.getMainRentRankList();
		model.addAttribute("main_rankList", main_rankList);
		model.addAttribute("main_rentRankList", main_rentRankList);
		
		ArrayList<adminVO> main_notices = mainService.getMainNotices();
		model.addAttribute("main_notices", main_notices);
		
		if(vo.getUser_id() != null) {
			model.addAttribute("vo", vo);
		}
		return "main";
	}
	
    @PostMapping("/user_logout")
    public String user_logout(@ModelAttribute("vo") UserVO vo, SessionStatus status, RedirectAttributes RA) {
    	status.setComplete();
    	RA.addFlashAttribute("msg", "로그아웃 되었습니다.");
    	return "redirect:/main";
    }
}
