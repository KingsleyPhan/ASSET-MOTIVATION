package com.pts.motivation.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pts.motivation.common.SessionParams;
import com.pts.motivation.dao.MoveObjectSelectDao;
import com.pts.motivation.model.MoveObject;

@Controller
@RequestMapping("ChoMuonTrongHeThong")
public class ChoMuonTrongHeThongController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();	
		
		MoveObject moveObject = new MoveObject();
		moveObject.setCmpnCd(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD)+"");
		MoveObjectSelectDao select = new MoveObjectSelectDao(moveObject);
		try {
			List<MoveObject> lstMove = select.excute();
			if(lstMove.size() > 0) {
				mv.addObject("lst", lstMove);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("moveObject", moveObject);
		mv.setViewName("ChoMuonTrongHeThong");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, params="search")
	public ModelAndView swarch(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		ModelAndView mv = new ModelAndView();	
		
		moveObject.setCmpnCd(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD)+"");
		MoveObjectSelectDao select = new MoveObjectSelectDao(moveObject);
		try {
			List<MoveObject> lstMove = select.excute();
			if(lstMove.size() > 0) {
				mv.addObject("lst", lstMove);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("moveObject", moveObject);
		mv.setViewName("ChoMuonTrongHeThong");
		return mv;
	}
	
	
	@RequestMapping(params="create" , method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?mode=CM");
		return mv;
	} 
	
	@RequestMapping(params="LenhDiChuyenNoiBo" , method = RequestMethod.POST)
	public ModelAndView LenhDiChuyenNoiBo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?mode=0");
		return mv;
	} 
	
	@RequestMapping(params="TraTaiSanMuon" , method = RequestMethod.POST)
	public ModelAndView TraTaiSan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?mode=TM");
		return mv;
	} 
	@RequestMapping(params="ThueTaiSan" , method = RequestMethod.POST)
	public ModelAndView ThueTaiSan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?mode=TH");
		return mv;
	} 
	@RequestMapping(params="TraThueTaiSan" , method = RequestMethod.POST)
	public ModelAndView TraThueTaiSan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?mode=TTH");
		return mv;
	} 
	
	@RequestMapping(params="BanGiaoTaiSan" , method = RequestMethod.POST)
	public ModelAndView BanGiaoTaiSan(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?mode=BG");
		return mv;
	} 
	
	@RequestMapping(params="edit" , method = RequestMethod.POST)
	public ModelAndView Edit(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/TaoLenhChoMuonTrongHeThong?moveId="+ request.getParameter("id"));
		return mv;
	} 
	
	@RequestMapping(params="declare" , method = RequestMethod.POST)
	public ModelAndView declare(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/KhaiBaoChoMuonTrongHeThong?moveId="+ request.getParameter("id"));
		return mv;
	} 
	
	@RequestMapping(params="PCD" , method = RequestMethod.POST)
	public ModelAndView PCD(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/PhongCoDien?moveId="+ request.getParameter("id"));
		return mv;
	} 
	@RequestMapping(params="KT" , method = RequestMethod.POST)
	public ModelAndView KT(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/PhongKeToan?moveId="+ request.getParameter("id"));
		return mv;
	} 
	@RequestMapping(params="KV" , method = RequestMethod.POST)
	public ModelAndView KV(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/PhongKhoVan?moveId="+ request.getParameter("id"));
		return mv;
	} 
	@RequestMapping(params="view" , method = RequestMethod.POST)
	public ModelAndView view(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/XemLenh?moveId="+ request.getParameter("id") +"&pageBack="+request.getParameter("backurl"));
		return mv;
	} 
}
