package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final TestService testService;
	
	@GetMapping("/")
	@ResponseBody
	public String mainPage() {
		System.out.println("get main.");
		return "main.";
	}
	
	@GetMapping("/test")
	public String test(Model model, @RequestParam(defaultValue="1") int page) {
		int pageSz=5, pageNum=5;
		int totalPageNum=testService.getPageSize(pageSz),
			start=(page-1)/pageNum * pageNum + 1, 
			end=Math.min(start+pageNum-1, totalPageNum);
		int prev = pageNum * ((page-1)/pageNum),
			next = pageNum * ((page-1)/pageNum + 1) + 1;
		
		System.out.println("totalPageNum : " + totalPageNum);
		System.out.println("start : " + start + ", end : " + end);
		System.out.println(testService.getList(page-1, pageSz));
		System.out.println(testService.getPageSize(pageSz));
		
		model.addAttribute("testList", testService.getList(page-1, pageSz));
		model.addAttribute("curPage", page);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("totalPageNum", totalPageNum);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		return "main";
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("login controller.");
		return "login";
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		System.out.println("login error.");
		model.addAttribute("error", true);
		return "login";
	}
	
	@PostMapping("/register")
	@ResponseBody
	public String register(@RequestBody RegisterDTO registerDTO) {
		return testService.register(registerDTO);
	}
}
