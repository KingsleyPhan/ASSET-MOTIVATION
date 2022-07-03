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
import com.pts.motivation.dao.SelectDepartmentDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

@Controller
@RequestMapping("exportDiChuyenNoiBo")
public class DiChuyenNoiBoExportController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		ShowDataCommon(model);
		
		String CouponCd = request.getParameter("lenh");
		String numberRow = "0";
		if(UtilCommon.isEmpty(CouponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			LENHDICHUYENNOIBO lenh = new LENHDICHUYENNOIBO();
			lenh.setId(CouponCd);
			SelectDiChuyenNoiBoDao ex = new SelectDiChuyenNoiBoDao(lenh);
			mv.addObject("abc", "thinh");
			try {
				List<LENHDICHUYENNOIBO> lst = ex.excute();
				if(lst.size()==1) {
					TaoLenhDiChuyenNoiBoForm TaoLenhDiChuyenNoiBoForm = new TaoLenhDiChuyenNoiBoForm();
					TaoLenhDiChuyenNoiBoForm.setId(lst.get(0).getId().trim());		
					TaoLenhDiChuyenNoiBoForm.setNoNumber(lst.get(0).getNoNumber());
					TaoLenhDiChuyenNoiBoForm.setSticker(lst.get(0).getSticker());
					TaoLenhDiChuyenNoiBoForm.setDeptIn(lst.get(0).getDeptIn().trim());
					TaoLenhDiChuyenNoiBoForm.setDeptOut(lst.get(0).getDeptOut().trim());
					TaoLenhDiChuyenNoiBoForm.setDeptOutName(lst.get(0).getDeptOutName().trim());
					TaoLenhDiChuyenNoiBoForm.setDateOut(lst.get(0).getDateStart().trim());
					TaoLenhDiChuyenNoiBoForm.setReason(lst.get(0).getReason());
					TaoLenhDiChuyenNoiBoForm.setCommentCreate(lst.get(0).getCommentCreate());
					mv.addObject("TaoLenhDiChuyenNoiBoForm", TaoLenhDiChuyenNoiBoForm);
					
				}
				
				SelectDiChuyenNoiBoChiTietDao exAsset = new SelectDiChuyenNoiBoChiTietDao(lenh);
				
				LENHDICHUYENNOIBO lenhChiTiet = exAsset.excute();
				
				if(lenhChiTiet.getLstAsset().size()==0) {
					numberRow= "1";
				} else {
					numberRow = lenhChiTiet.getLstAsset().size()+"";
				}
				
				mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
				mv.addObject("numberRow", numberRow);
			
				
				
				
				
				mv.setViewName("ExportDiChuyenNoiBo");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		mv.setViewName("ExportDiChuyenNoiBo");
		return mv;
	}
	
	public void ShowDataCommon(Model model) {
		SelectDepartmentDao ex = new SelectDepartmentDao("202012151019454");
		try {
			List<DEPARTMENT_S> LISTDEPT = ex.excute();
			model.addAttribute("lstDept", new GetMap().getMapDepartment(LISTDEPT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
