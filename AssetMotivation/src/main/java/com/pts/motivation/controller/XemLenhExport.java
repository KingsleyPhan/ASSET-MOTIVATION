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
import com.pts.motivation.dao.SelectDepartmentDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;
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
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
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
