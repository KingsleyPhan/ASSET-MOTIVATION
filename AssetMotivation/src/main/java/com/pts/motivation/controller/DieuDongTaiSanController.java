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
import com.pts.motivation.dao.MoveObjectDetailSelectDao;
import com.pts.motivation.dao.MoveObjectSelectDao;
import com.pts.motivation.model.MoveObject;

@Controller
@RequestMapping("DieuDongTaiSan")
public class DieuDongTaiSanController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		
		ModelAndView mv = new ModelAndView();
		
		String ThisCompany = request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString();
		
		MoveObject moveObject = new MoveObject();
		moveObject.setCmpnCd(ThisCompany);
		
//		MoveObjectSelectDao selectMoveOb = new MoveObjectSelectDao(moveObject);
//		List<MoveObject> lstMove = null;
//		try {
//			lstMove = selectMoveOb.excute();
//			for(int i =0;i<lstMove.size();i++) {
//				MoveObjectDetailSelectDao selectDetail  = new MoveObjectDetailSelectDao(lstMove.get(i));
//				lstMove.get(i).setLstAsset(selectDetail.excute().getLstAsset());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		model.addAttribute("moveObject", new MoveObject());
		//mv.addObject("lstMove", lstMove);
		mv.setViewName("DieuDongTaiSan");
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView iniPOSTt(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		
		ModelAndView mv = new ModelAndView();
		
		String ThisCompany = request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString();
		
		//MoveObject moveObject = new MoveObject();
		moveObject.setCmpnCd(ThisCompany);
		
		MoveObjectSelectDao selectMoveOb = new MoveObjectSelectDao(moveObject);
		List<MoveObject> lstMove = null;
		try {
			lstMove = selectMoveOb.excute();
			for(int i =0;i<lstMove.size();i++) {
				lstMove.get(i).setNameAsset(moveObject.getNameAsset());
				lstMove.get(i).setModelAsset(moveObject.getModelAsset());
				lstMove.get(i).setSeriesAsset(moveObject.getSeriesAsset());
				MoveObjectDetailSelectDao selectDetail  = new MoveObjectDetailSelectDao(lstMove.get(i));
				lstMove.get(i).setLstAsset(selectDetail.excute().getLstAsset());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("moveObject", moveObject);
		mv.addObject("lstMove", lstMove);
		mv.setViewName("DieuDongTaiSan");
		return mv;
	}
}
