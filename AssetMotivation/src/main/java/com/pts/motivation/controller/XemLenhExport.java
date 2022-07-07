package com.pts.motivation.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pts.motivation.common.GetMap;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.MoveObjectDetailSelectDao;
import com.pts.motivation.dao.MoveObjectSelectDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.MoveObject;

@Controller
@RequestMapping("XemLenhExport")
public class XemLenhExport {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		String id  = request.getParameter("lenh");
		MoveObject moveOb = new MoveObject();
		if(UtilCommon.isEmpty(id) == false) {
			moveOb.setId(id);
			
			MoveObjectSelectDao moveSelect = new MoveObjectSelectDao(moveOb);
			try {
				List<MoveObject> lstMove = moveSelect.excute();
				if(lstMove.size()==1) {
					model.addAttribute("moveObject", lstMove.get(0));
					moveOb =  lstMove.get(0);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
		
		String date  = UtilCommon.getDateCurrent("dd/MM/yyyy"); 
		
		String dateGet  = moveOb.getDateCreate().substring(0, 10);
		System.out.println("date: yeyeye: '" + dateGet+"'");
		
		String[] arrayDay = dateGet.split("/"); 
		String DatString = "Ngày " + arrayDay[0] +  " tháng " + arrayDay[1] + " năm " + arrayDay[2];
		mv.addObject("dateString", DatString);
		
		if(moveOb.getUserAccount()!= null && moveOb.getUserAccount().trim().length() > 0) {
			model.addAttribute("isAccount", "true");
		} else {
			model.addAttribute("isAccount", "false");
		}
		
		if(moveOb.getUserManager()!= null && moveOb.getUserManager().trim().length() > 0) {
			model.addAttribute("isManager", "true");
		} else {
			model.addAttribute("isManager", "false");
		}
		
		if(moveOb.getUserCreate()!= null &&  moveOb.getUserCreate().trim().length() > 0) {
			model.addAttribute("isCreate", "true");
		} else {
			model.addAttribute("isCreate", "false");
		}
		
		
		String numberRow = "0";
		
		MoveObjectDetailSelectDao exAsset = new MoveObjectDetailSelectDao(moveOb);
		
		MoveObject lenhChiTiet;
		try {
			lenhChiTiet = exAsset.excute();
			if(lenhChiTiet.getLstAsset().size()==0) {
				numberRow= "1";
			} else {
				numberRow = lenhChiTiet.getLstAsset().size()+"";
			}
			
			mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.addObject("numberRow", numberRow);
		
		
		mv.setViewName("ExportLenh");
		return mv;
	}
	
	
}
