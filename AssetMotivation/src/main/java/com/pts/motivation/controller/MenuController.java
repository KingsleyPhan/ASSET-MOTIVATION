package com.pts.motivation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("Menu")
public class MenuController {
	
	@RequestMapping(params="DiChuyenNoiBo", method = RequestMethod.POST)
	public String DiChuyenNoiBo(HttpServletRequest request) {
		return "redirect:/DiChuyenNoiBo";
	}
	
	@RequestMapping(params="DieuDongTaiSan", method = RequestMethod.POST)
	public String DieuDongTaiSan(HttpServletRequest request) {
		return "redirect:/DieuDongTaiSan";
	}
	
	@RequestMapping(params="ChoMuonTrongHeThong", method = RequestMethod.POST)
	public String ChoMuonTrongHeThong(HttpServletRequest request) {
		return "redirect:/ChoMuonTrongHeThong";
	}
}
