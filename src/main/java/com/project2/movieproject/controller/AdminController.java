package com.project2.movieproject.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project2.movieproject.admin.AdminService;
import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.MovieVO;
import com.project2.movieproject.command.PageVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.adminVO;
import com.project2.movieproject.command.qaVO;
import com.project2.movieproject.user.UserService;

@Controller
@SessionAttributes("vo")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;
	//게시판
	@GetMapping("/adminMain1")
	public String adminMain1(Model model,
							 @ModelAttribute("vo")UserVO uservo1) {
		
		ArrayList<UserVO> uservo = new ArrayList<>();
		String month;
		for ( int i = 0 ; i <= 11 ; i++) {
			if(i <10) {
				month = "2022-0"+(i+1)+'%';
			}else {
				month = "2022-"+(i+1)+'%';				
			}
			
			uservo.add(userService.getUserCount(month));
		}
		model.addAttribute("UserVO", uservo);
		model.addAttribute("vo", uservo1);
		return "admin/adminMain1";
	}
	
	//문의사항
	@GetMapping("/qna")
	public void qna(Model model, @ModelAttribute("vo")UserVO uservo) {
		
		
		ArrayList<qaVO> list = userService.qa_list();
	
		model.addAttribute("vo",uservo);
		model.addAttribute("list", list);
	}
	@GetMapping("/qnaForm")
		public String qa_table(Model model, Criteria cri){
			
			ArrayList<qaVO> list  =  userService.qa_table(cri);
			
			int total=adminService.total(cri);
			PageVO pageVO = new PageVO(cri, total);
			model.addAttribute("list", list);
			model.addAttribute("pageVO",pageVO);
			return "admin/qna";
			
		}
	@GetMapping("/searchForm")
	public String searchTable(Model model, Criteria cri,MovieVO vo){
		
		ArrayList<MovieVO> list = adminService.searchTable(cri);
		int total = adminService.total(cri);
		PageVO pageVO = new PageVO(cri,total);

		model.addAttribute("list", list);
	
		model.addAttribute("pageVO", pageVO);
		return "admin/searchlist";
	}
	@GetMapping("/searchlist")//search main view
	public void searchlist(Model model, MovieVO vo) {
		
		ArrayList<MovieVO> list = adminService.searchlist();

		model.addAttribute("list",list);
		model.addAttribute("movieVO", vo);
	}
	

	//화면폼
	@GetMapping("/notice_regist2")
	public String notice_regist2() {

		return "admin/notice_regist2";
	}

	@PostMapping("/noticeRegist")//등록폼(나중에 첨부파일도)

	public String noticeRegist(adminVO vo, RedirectAttributes RA,@RequestParam("file") MultipartFile list,Model model,@ModelAttribute("vo")UserVO uservo1 )
		 {
				/*
				 * for(MultipartFile f:list) { System.out.println(f.isEmpty());
				 * System.out.println(f.getContentType()); } System.out.println(vo.toString());
				 */
		
		
	
		 
		model.addAttribute("vo1","uservo1");
		int result = adminService.noticeRegist(vo,list);

		if(result == 1) {
			RA.addFlashAttribute("msg", vo.getAdmin_id()+"등록이 성공했습니다.");
		}else {
			RA.addFlashAttribute("msg", "등록에 실패했습니다.");
		}

	

	return"redirect:/admin/notices";
}
	


	@GetMapping("/notices")//목록
	public String notices(Model model,Criteria cri, @ModelAttribute("vo")UserVO uservo,RedirectAttributes RA) {

		ArrayList<adminVO> list=adminService.List(cri);
		int total=adminService.total(cri);


		PageVO pageVO = new PageVO(cri, total);

		model.addAttribute("vo",uservo);
		
		model.addAttribute("list", list); 
		model.addAttribute("pageVO", pageVO);
		
		
		if(uservo.getUser_id() == null || uservo.getUser_id() == "") {
			RA.addFlashAttribute("msg", "로그인을 하셔야 공지사항을 보실수 있습니다.");	
			return "redirect:/main";
		
			
		}
		
		return "admin/notices";
	}

	@GetMapping("/notice_regist")//
	public String notice_regist(@RequestParam("admin_key") int admin_key, 
			Model model,@ModelAttribute("vo")UserVO uservo1) {

		adminVO adminvo = adminService.getcontent(admin_key);
		model.addAttribute("adminvo", adminvo);

		int hit =adminService.hit(adminvo);
		model.addAttribute("hit",hit);
		
		
		model.addAttribute("vo" , uservo1);
		
		
		
		return "admin/notice_regist";
	}

	
	
	

	@PostMapping("/noticeUpdate")//게시글 수정하기
	public String noticeUpdate(@Valid adminVO adminvo, 
			Model model,RedirectAttributes RA,
			Errors erros, @RequestParam("file") MultipartFile f) {
		
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String nowdate = sd.format(new Date());
		
		model.addAttribute("nowdate", nowdate);
		
		int result = adminService.update(adminvo,f);

		if(result == 1) {
			RA.addFlashAttribute("msg", "수정이 성공했습니다.");
		}else {
			RA.addFlashAttribute("msg", "수정에 실패했습니다.");
		}

		return "redirect:/admin/notices";
	}

	@PostMapping("/noticeDelete")//삭제
	public String noticeDelete(@RequestParam("admin_key") int admin_key,
								RedirectAttributes RA) {

		int result = adminService.delete(admin_key);
		
		if(result == 1) {
			
			RA.addFlashAttribute("msg", "삭제에 성공했습니다.");

		}else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다.");
		}

		return "redirect:/admin/notices";
	}

	//api에서 영화를 검색해서 DB에 등록하는 페이지
	@GetMapping("/adminMovieReg")
	public String adminMovieReg(@ModelAttribute("vo")UserVO uservo1, Model model) {
		model.addAttribute("vo" , uservo1);
		return "admin/adminMovieReg";
	}

	//api에서 영화를 검색해서 DB에 등록하는 폼
	@PostMapping("/movieRegForm")
	public String movieRegForm(MovieVO vo,
			RedirectAttributes RA) {
		int result = adminService.movieRegist(vo);

		if(result > 0) { //성공
			RA.addFlashAttribute("msg", "영화가 정상 등록되었습니다" );
		} else { //실패
			RA.addFlashAttribute("msg", "영화가 등록되지 않았습니다. 관리에게 문의하세요.");
		}

		return "redirect:/admin/adminMovieReg";
	}

	// DB에 있는 영화 목록페이지
	@GetMapping("/adminMovieList")
	public String adminMovieList(Model model, Criteria cri, @ModelAttribute("vo")UserVO uservo1) {
		model.addAttribute("vo" , uservo1);
		ArrayList<MovieVO> list = adminService.getMovieList(cri);
		int total = adminService.getTotal(cri);
		
		PageVO pageVO = new PageVO(cri, total);

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		return "admin/adminMovieList";
	}

	// DB에 있는 영화 목록페이지에서 상세화면
	@GetMapping("/adminMovieDetail")
	public String adminMovieDetail(@RequestParam("movie_koficCd") int movie_koficCd,
			Model model, @ModelAttribute("vo")UserVO uservo1) {
		model.addAttribute("vo" , uservo1);
		MovieVO movievo = adminService.getMovieDetail(movie_koficCd);
		model.addAttribute("movievo", movievo);

		return "admin/adminMovieDetail";
	}
	
	@PostMapping("/movieUpdate")
	public String movieUpdate(MovieVO movieVO, RedirectAttributes RA, @ModelAttribute("vo")UserVO uservo1, Model model) {
		model.addAttribute("vo" , uservo1);
		System.out.println(movieVO.getMovie_vUrl());
		System.out.println(movieVO.getMovie_sellPrice());
		System.out.println(movieVO.getMovie_rentPrice());
		int result = adminService.movieUpdate(movieVO);
		System.out.println(movieVO.getMovie_vUrl());
		System.out.println(movieVO.getMovie_sellPrice());
		System.out.println(movieVO.getMovie_rentPrice());
		if(result == 1 ) {
			RA.addFlashAttribute("msg", movieVO.getMovie_nm() + "이 수정되었습니다");
		} else {
			RA.addFlashAttribute("msg", "수정에 실패했습니다. 관리자에게 문의하세요");
		}
		
		return "redirect:/admin/adminMovieList";
	}
	


	@GetMapping("/user_Info")
	public String userMypage(@ModelAttribute("vo") UserVO vo, Model model) {
		String db_id = vo.getUser_id();
		
		

		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		
		model.addAttribute("userdata", userdata);
		return "admin/user_info";
	}
	@GetMapping("/replyPage")
	public String replyPage(@RequestParam("qa_key") Integer qa_key, Model model) {
		ArrayList<qaVO> qa_data = userService.qa_read(qa_key);
		model.addAttribute("qa_data", qa_data);

		return "admin/replyPage";

	}
	
	@PostMapping("/qa_update")
	public String qa_update(qaVO vo, Model model, RedirectAttributes RA) {
		int qa_update = userService.qa_update(vo);
		
		if(qa_update == 1 ) {
			RA.addFlashAttribute("msg", "답변이 등록되었습니다.");
		} else {
			RA.addFlashAttribute("msg", "실패했습니다. 관리자에게 문의하세요");
		}
		return "redirect:/admin/qna";
	}
	
	@GetMapping("/mapMain")
	public void mapMain() {
		
	}
	
	@PostMapping("/user_logout")
    public String user_logout(@ModelAttribute("vo") UserVO vo, SessionStatus status, RedirectAttributes RA) {
    	status.setComplete();
    	RA.addFlashAttribute("msg", "로그아웃 되었습니다.");
    	return "redirect:/main";
    }
}
