package com.project2.movieproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project2.movieproject.command.CommentVO;
import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.PageVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.qaVO;
import com.project2.movieproject.user.EmailServiceImpl;
import com.project2.movieproject.user.OtherService;
import com.project2.movieproject.user.UserService;


@SessionAttributes("vo")
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	OtherService service;
	
	@ModelAttribute("vo")
	public UserVO setUserVO() {
		System.out.println("***********setUserVO()**********");
		return new UserVO();
	}
	
	@GetMapping("/userLogin")
	public String userLogin(SessionStatus status) {

    	status.setComplete();
		return "user/userLogin";
	}
	
	@GetMapping("/userRegist")
	public String userRegist() {
		
		return "user/userRegist";
	}
	
	@GetMapping("/userMypage")
	public String userMypage(@ModelAttribute("vo") UserVO vo, Model model, HttpServletRequest request) {
		String db_id = vo.getUser_id();
		String prevurl = request.getHeader("referer");
		if(vo.getUser_id()==null) {
			return "redirect:/user/userLogin";
		}
		String dttg;
		String a4;
		if(prevurl.equals("http://3.36.178.116:8383/user/userQnaRegist")) {
			dttg = "#qna";
			a4 = "#a4";
			model.addAttribute("dttg", dttg);
			model.addAttribute("a4", a4);
		}

		ArrayList<UserVO> userdata = userService.userdata(db_id);
		ArrayList<qaVO> myqalist = userService.myqa_read(db_id);
		ArrayList<CommentVO> mycomment = userService.mycomment(vo);
		ArrayList<UserVO> movie_like = userService.movie_like(vo);
		ArrayList<UserVO> user_buy_list = userService.user_buy_list(vo);
		ArrayList<UserVO> user_rent_list = userService.user_rent_list(vo);
		
		
		
		
		model.addAttribute("userdata", userdata);
		model.addAttribute("myqalist", myqalist);
		model.addAttribute("mycomment", mycomment);
		model.addAttribute("movie_like", movie_like);
		model.addAttribute("user_buy_list", user_buy_list);
		model.addAttribute("user_rent_list", user_rent_list);
		return "user/userMypage";
	}
	
	@GetMapping("/userQnaRegist")
	public String userQnaRegist(@ModelAttribute("vo") UserVO vo, Model model, RedirectAttributes RA) {
		if(vo.getUser_id() == null) {
			RA.addFlashAttribute("msg", "로그인 후 이용해주세요.");
			return "redirect:/main";
		}
		model.addAttribute("vo", vo);
		return "user/userQnaRegist";
	}
	
	@PostMapping("/qa_regist")
	public String qa_regist(@Valid qaVO vo, Errors errors, Model model, RedirectAttributes RA) {
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err: list) {
				if(err.isBindingFailure()) {
					RA.addFlashAttribute("msg", "제목과 내용을 작성해주세요");
				} else {
					RA.addFlashAttribute("msg", "제목과 내용을 작성해주세요");
				}
			}
			return "redirect:/user/userQnaRegist";
		}

		int result = userService.qa_regist(vo);
		if(result == 1) { //성공
			RA.addFlashAttribute("msg", vo.getQa_title() + "이 정상 등록");
		} else { //실패
			RA.addFlashAttribute("msg", "등록 실패, 관리자에게 문의하세요.");
		}
		return "redirect:/user/userMypage";
	}
	@GetMapping("/userPhone")
	public String userPhone() {
		return "user/userPhone";
	}
	
	@PostMapping("/user_update_phone")
	public String user_update_phone(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		
		if(newvo.getUser_phone().equals(userdata.get(0).getUser_phone())) {
			System.out.println("핸드폰번호 확인 완료");
			
			vo.setUser_newphone(newvo.getUser_newphone());
			/*
			 * int result = userService.user_update(newvo); model.addAttribute("result",
			 * result); if(result == 1 ) { RA.addFlashAttribute("msg", vo.getUser_id() +
			 * "의 핸드폰번호가 변경되었습니다"); } else { RA.addFlashAttribute("msg",
			 * "수정에 실패했습니다. 관리자에게 문의하세요"); }
			 */
			return "redirect:/user/usermailCheck";
		} else {
			RA.addFlashAttribute("msg", "입력한 현재 핸드폰번호가 일치하지않습니다. 다시 시도해주세요.");
			System.out.println("번호가 일치하지 않음");
		}

		return "redirect:/user/userPhone";
	}
	
	@GetMapping("/userEmail")
	public String userEmail() {
		return "user/userEmail";
	}
	
	@PostMapping("/user_update_email")
	public String user_update_email(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		
		
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		
		if(newvo.getUser_email().equals(userdata.get(0).getUser_email())) {
			System.out.println("이메일 확인 완료");
			vo.setUser_newemail(vo.getUser_newemail());
			/*
			 * int result = userService.user_update(newvo); model.addAttribute("result",
			 * result); if(result == 1 ) { RA.addFlashAttribute("msg", vo.getUser_id() +
			 * "의 이메일이 변경되었습니다"); } else { RA.addFlashAttribute("msg",
			 * "수정에 실패했습니다. 관리자에게 문의하세요"); }
			 */
			return "redirect:/user/usermailCheck";
		} else {
			RA.addFlashAttribute("msg", "입력한 현재 이메일이 일치하지않습니다. 다시 시도해주세요.");
			System.out.println("이메일이 일치하지 않음");
		}

		return "redirect:/user/userEmail";
	}
	
	@GetMapping("/userPassword")
	public String userPassword() {
		return "user/userPassword";
	}
	
	@PostMapping("/user_update_password")
	public String user_update_password(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		
		if(newvo.getUser_password().equals(userdata.get(0).getUser_password())) {
			System.out.println("비밀번호 확인완료");
			
			vo.setUser_newpassword(newvo.getUser_newpassword());
			/*
			 * int result = userService.user_update(newvo); model.addAttribute("result",
			 * result); if(result == 1 ) { RA.addFlashAttribute("msg", vo.getUser_id() +
			 * "의 비밀번호가 변경되었습니다"); } else { RA.addFlashAttribute("msg",
			 * "수정에 실패했습니다. 관리자에게 문의하세요"); }
			 */
			return "redirect:/user/usermailCheck";
		} else {
			RA.addFlashAttribute("msg", "입력한 현재 비밀번호가 일치하지않습니다. 다시 시도해주세요.");
			System.out.println("비밀번호가 일치하지 않음");
		}

		return "redirect:/user/userPassword";
	}
	
	@GetMapping("/userQnaRead")
	public String userQnaRead(@RequestParam("qa_key") Integer qa_key, Model model) {
		ArrayList<qaVO> myqa = userService.qa_read(qa_key);
		model.addAttribute("myqa", myqa);
		return "user/userQnaRead";
	}
	
	@GetMapping("/userBirth")
	public String userBirth() {
		return "user/userBirth";
	}
	
	@PostMapping("/user_update_birth")
	public String user_update_birth(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		newvo.setUser_id(db_id);
		
		if(newvo.getUser_birth().equals(userdata.get(0).getUser_birth())) {
			System.out.println("생년월일 확인 완료");
			
			vo.setUser_newbirth(newvo.getUser_newbirth());
			/*
			 * int result = userService.user_update(newvo); model.addAttribute("result",
			 * result); if(result == 1 ) { RA.addFlashAttribute("msg", vo.getUser_id() +
			 * "의 생년월일이 변경되었습니다"); } else { RA.addFlashAttribute("msg",
			 * "수정에 실패했습니다. 관리자에게 문의하세요"); }
			 */
			
			return "redirect:/user/usermailCheck";
		} else {
			RA.addFlashAttribute("msg", "입력한 현재 생년월일이 일치하지않습니다. 다시 시도해주세요.");
			System.out.println("날짜가 일치하지 않음");
		}
		return "redirect:/user/userBirth";
	}
	
	//회원가입 폼
	@PostMapping("/RegistForm")
	public String RegistForm(@Valid UserVO vo, Errors errors,
							 RedirectAttributes RA, Model model) throws Exception {
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err: list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			model.addAttribute("vo", vo);
			return "user/userRegist";
		}
		
		int result = userService.regist(vo);
		if(result == 1) { //성공
			ArrayList<UserVO> userdata = userService.userdata(vo.getUser_id());
			model.addAttribute("vo", userdata);
			RA.addFlashAttribute("msg", vo.getUser_id() + "이 정상 등록, 이메일인증 하세요.");
		} else { //실패
			RA.addFlashAttribute("msg", "등록 실패, 관리자에게 문의하세요.");
		}
		return "redirect:/user/usermailCheck";	//등록이후 로그인화면
	}
	
	//로그인 폼
	@PostMapping("/LoginForm")
	public String LoginForm(UserVO vo,
							RedirectAttributes RA,
							Model model) {
		String db_id = vo.getUser_id();
        int count_id = userService.idCheck(db_id);
		
		ArrayList<UserVO> userdata = userService.userdata(db_id);

		if(count_id > 0) {
			if(vo.getUser_password().equals(userdata.get(0).getUser_password())) { //성공
				if(userdata.get(0).isUser_auth()) {
					model.addAttribute("vo", userdata);
					if(userdata.get(0).isUser_admin() == true) {
						return "redirect:/admin/adminMain1";
					}
					return "redirect:/main";
				} else {
					model.addAttribute("vo", userdata);
					RA.addFlashAttribute("msg", "이메일 인증이 되어있지 않습니다. 인증을 진행해주세요.");
					return "redirect:/user/usermailCheck";
				}
			} else { //실패
				RA.addFlashAttribute("msg", "로그인 실패, 아이디와 비밀번호를 확인하세요.");
				return "redirect:/user/userLogin";
			}
		} else {
			RA.addFlashAttribute("msg", "로그인 실패, 아이디와 비밀번호를 확인하세요.");
			return "redirect:/user/userLogin";
		}
		
	}
	
    // 아이디 체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(UserVO vo, Model model, RedirectAttributes RA, @RequestParam("id") String id){
        int cnt = userService.idCheck(id);
        return cnt;
    }
    
    @PostMapping("/emailCheck")
    @ResponseBody
    public int emailCheck(UserVO vo, @RequestParam("email") String email) {
    	int emailcnt = userService.emailCheck(email);
    	return emailcnt;
    }
    
    // 계정 삭제
    @PostMapping("/user_drop")
    public String user_drop(@ModelAttribute("vo") UserVO vo, Model model, RedirectAttributes RA) {
    	
    	vo.setUser_delete_check("1");
    	
    	return "redirect:/user/usermailCheck";
		/*
		 * int drop = userService.user_delete(vo);
		 * 
		 * if(drop > 0) { //성공 RA.addFlashAttribute("msg", vo.getUser_id() +
		 * "삭제되었습니다."); return "redirect:/user/userLogin"; } else { //실패
		 * RA.addFlashAttribute("msg", "삭제 실패, 관리자에게 문의하세요."); return
		 * "redirect:/user/userLogin"; }
		 */
    }
    
    @GetMapping("/userDetail")
    public String userDetail(@RequestParam("user_id") String dbid, Model model) {
    	
		ArrayList<UserVO> userdata = userService.userdata(dbid);
		ArrayList<UserVO> user_buy_list = userService.user_buy_list(userdata.get(0));
		ArrayList<UserVO> user_rent_list = userService.user_rent_list(userdata.get(0));
		model.addAttribute("user_buy_list", user_buy_list);
		model.addAttribute("user_rent_list", user_rent_list);
    	
    	return "user/userDetail";
    }
    
    @GetMapping("/userFind")
    public String userFind() {
    	return "user/userFind";
    }
    
    @PostMapping("/find_id")
    public String find_id(UserVO vo, RedirectAttributes RA, Model model ) {
    	int count = userService.FindId(vo);
    	if(count > 0 ) {
    		model.addAttribute("vo", vo);
    		return "redirect:/user/usermailCheck";
    	}else {
			RA.addFlashAttribute("msg", "입력한 조건의 아이디가 없습니다. 다시한번 확인해주세요.");
    		return "redirect:/user/find_id";
    	}
    }
    
    @GetMapping("/userFindPw")
    public String userFindPw() {
    	return "user/userFindPw";
    }
    
    @GetMapping("/userPwUpdate")
    public String userPwUpdate() {
    	return "user/userPwUpdate";
    }
    
    @PostMapping("/update_pw")
    public String update_pw(@ModelAttribute("vo") UserVO vo, Model model, UserVO newvo, RedirectAttributes RA) {
    	String newPassword = newvo.getUser_newpassword();
		vo.setUser_newpassword(newPassword);
		System.out.println(vo.getUser_newpassword());
		int result = userService.user_update(vo);
		
		if(result > 0) {
			RA.addFlashAttribute("msg", "비밀번호가 변경되었습니다. 로그인을 다시 해주세요.");
			return "redirect:/user/find_id";
		} else {
			RA.addFlashAttribute("msg", "비밀번호 변경 실패");
	    	return "redirect:/user/find_id";
		}
		
    }
    
    @PostMapping("/find_pw")
    public String find_pw(UserVO vo, RedirectAttributes RA, Model model) {
    	int count = userService.FindPw(vo);
    	if(count > 0 ) {
    		model.addAttribute("vo", vo);
    		return "redirect:/user/usermailCheck";
    	}else {
			RA.addFlashAttribute("msg", "입력한 조건의 아이디가 없습니다. 다시한번 확인해주세요.");
    		return "redirect:/user/find_id";
    	}
    }
    
    @GetMapping("/find_id")
    public String find_id2() {
    	return "user/find_id";
    }
    
    
    @GetMapping("/userInfo")
    public String userInfo(Model model,Criteria cri, @ModelAttribute("vo") UserVO vo1) {
    	
      ArrayList<UserVO> userlist = userService.userlist(cri);
      int total =userService.total(cri);
      
      PageVO pageVO = new PageVO(cri,total);
      model.addAttribute("vo1", vo1);
      model.addAttribute("userlist", userlist);
      model.addAttribute("pageVO",pageVO);
       return "user/userInfo";
    }
    
    @GetMapping("/usermailCheck")
	public void emailConfirm(@ModelAttribute("vo") UserVO vo, Model model)throws Exception{
		service.sendSimpleMessage(vo.getUser_email());	
		System.out.println("전달 받은 이메일 : "+vo.getUser_email());
//		ArrayList<UserVO> userdata = userService.userdata(vo.getUser_id());
		model.addAttribute("userdata", vo);
	}
    
    @PostMapping("/verifyCode")
	public String verifyCode(String code, @ModelAttribute("vo") UserVO vo, SessionStatus status, RedirectAttributes RA) {
		
		System.out.println("code : "+code);
		System.out.println(vo.getUser_email());
		System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
		if(EmailServiceImpl.ePw.equals(code)) {
			
			if(vo.getUser_newemail() != null || vo.getUser_newpassword() != null || vo.getUser_newphone() != null || vo.getUser_newbirth() != null) {
				int result = userService.user_update(vo);
				if(result > 0) {
					RA.addFlashAttribute("msg", "개인정보가 변경되었습니다. 다시 로그인 해주세요.");
					status.setComplete();
					return "redirect:/user/find_id";
				}
				else {
					RA.addFlashAttribute("msg", "개인정보 변경실패. 관리자에게 문의하세요.");
					status.setComplete();
					return "redirect:/user/find_id";
				}
			}
			
			if(vo.getUser_delete_check() != null) {
				int result = userService.user_delete(vo);
				if(result > 0) {
					RA.addFlashAttribute("msg", "계정이 탈퇴되었습니다. 그동안 감사했습니다.");
					status.setComplete();
					return "redirect:/main";
				}
				else {
					RA.addFlashAttribute("msg", "계정탈퇴실패. 관리자에게 문의하세요.");
					status.setComplete();
					return "redirect:/user/userMypage";
				}
			}
			
			/*
			 * if(vo.getUser_newpassword() != null) { int result =
			 * userService.user_update(vo); if(result > 0) { RA.addFlashAttribute("msg",
			 * "비밀번호가 변경되었습니다."); status.setComplete(); return "redirect:/user/find_id"; }
			 * else { RA.addFlashAttribute("msg", "비밀번호 변경실패. 관리자에게 문의하세요.");
			 * status.setComplete(); return "redirect:/user/find_id"; } }
			 * 
			 * if(vo.getUser_newbirth() != null) { int result = userService.user_update(vo);
			 * if(result > 0) { RA.addFlashAttribute("msg", "생년월일이 변경되었습니다.");
			 * status.setComplete(); return "redirect:/user/find_id"; } else {
			 * RA.addFlashAttribute("msg", "생년월일 변경실패. 관리자에게 문의하세요."); status.setComplete();
			 * return "redirect:/user/find_id"; } }
			 * 
			 * if(vo.getUser_newphone() != null) { int result = userService.user_update(vo);
			 * if(result > 0) { RA.addFlashAttribute("msg", "생년월일이 변경되었습니다.");
			 * status.setComplete(); return "redirect:/user/find_id"; } else {
			 * RA.addFlashAttribute("msg", "생년월일 변경실패. 관리자에게 문의하세요."); status.setComplete();
			 * return "redirect:/user/find_id"; } }
			 */
			
			if(vo.getUser_id() == null) {
				String yourId = userService.FindyourId(vo);
				RA.addFlashAttribute("msg", "당신의 아이디는 " + yourId + " 입니다.");
				status.setComplete();
				return "redirect:/user/find_id";
			}
			if(vo.getUser_name() == null) {
				RA.addFlashAttribute("msg", "비밀번호를 변경해주세요.");
				return "redirect:/user/userPwUpdate";
			}
			RA.addFlashAttribute("msg", vo.getUser_id() + " 회원가입 성공");
			userService.auth_update(vo.getUser_id());
			status.setComplete();
			return "redirect:/user/userLogin";
		} else {
			int result = userService.user_delete(vo);
			if(result > 0) { //성공
				RA.addFlashAttribute("msg", vo.getUser_id() + "인증 실패. 다시 해주세요.");
				return "redirect:/user/userRegist";
			} else { //실패
			RA.addFlashAttribute("msg", "삭제 실패, 관리자에게 문의하세요.");
			return "redirect:/user/userLogin";
			}
		}
		

	}
    
    @PostMapping("/user_logout")
    public String user_logout(@ModelAttribute("vo") UserVO vo, SessionStatus status, RedirectAttributes RA) {
    	status.setComplete();
    	RA.addFlashAttribute("msg", "로그아웃 되었습니다.");
    	return "redirect:/main";
    }
    

}
