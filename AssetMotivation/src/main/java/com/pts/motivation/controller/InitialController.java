package com.pts.motivation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class InitialController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String init() {
			return "DangNhap";
	}
}
