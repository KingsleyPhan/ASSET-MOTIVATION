package com.pts.motivation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("DieuDongTaiSan")
public class DieuDongTaiSanController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String init() {
			return "DieuDongTaiSan";
	}
}
