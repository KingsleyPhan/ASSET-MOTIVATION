package com.pts.motivation.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.aspose.email.HtmlLoadOptions;
import com.pts.motivation.common.GetMap;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.SelectDepartmentDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoDao;
import com.pts.motivation.dao.SelectTaiSanDao;
import com.pts.motivation.dao.UpdateDeleteLenhDiChuyenNoiBoDao;
import com.pts.motivation.form.DiChuyenNoiBoForm;
import com.pts.motivation.form.TaiSanDiChuyenNoiBoForm;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.ASSET;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("SearchTaiSanDiChuyenNoiBo")
public class SearchTaiSanDiChuyenNoiBoController {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody 
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv  = new ModelAndView();
		
		if(!model.containsAttribute("TaiSanDiChuyenNoiBoForm")) {
			TaiSanDiChuyenNoiBoForm TaiSanDiChuyenNoiBoForm  = new TaiSanDiChuyenNoiBoForm();
			model.addAttribute("TaiSanDiChuyenNoiBoForm", TaiSanDiChuyenNoiBoForm);
		}
		
		SelectDepartmentDao ex = new SelectDepartmentDao((String) request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD));
		try {
			List<DEPARTMENT_S> LISTDEPT = ex.excute();
			model.addAttribute("lstDept", new GetMap().getMapDepartment(LISTDEPT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String deptCd = request.getParameter("dept");
		mv.addObject("deptCd", deptCd);
		ASSET assCheck = new ASSET();
		assCheck.setDeptCd(deptCd);
		SelectTaiSanDao SelectTaiSanCommon = new SelectTaiSanDao(assCheck);
		try {
			List<ASSET> lstCommon = SelectTaiSanCommon.excute();
			Map<String,String> lstModel = new HashMap<String,String>();
			Map<String,String> lstSeries = new HashMap<String,String>();
			Map<String,String> lstRfid = new HashMap<String,String>();
			Map<String,String> lstName = new HashMap<String,String>();
			lstModel.put("", "");
			lstSeries.put("", "");
			lstRfid.put("", "");
			lstName.put("", "");
			if(lstCommon != null) {
				for(int i=0; i<lstCommon.size();i++) {
					lstModel.put(lstCommon.get(i).getModel(), lstCommon.get(i).getModel());
					lstSeries.put(lstCommon.get(i).getSeries(), lstCommon.get(i).getSeries());
					lstRfid.put(lstCommon.get(i).getRfid(), lstCommon.get(i).getRfid());
					lstName.put(lstCommon.get(i).getName(), lstCommon.get(i).getName());
				}
			}
			model.addAttribute("lstDept", lstModel);
			model.addAttribute("lstSeries", lstSeries);
			model.addAttribute("lstRfid", lstRfid);
			model.addAttribute("lstName", lstName);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		SelectTaiSanDao selectEx = new SelectTaiSanDao(assCheck);
		try {
			List<ASSET> lst = selectEx.excute();
			if(lst.size() == 0) {
				UtilCommon.showMessageNotification(mv, "KHÔNG TÌM THẤY LỆNH NÀO");
			} else {
				UtilCommon.showMessageNotification(mv, "TÌM THẤY "+ lst.size() + " PHÙ HỢP");
				mv.addObject("lst",lst);
			}
			System.out.println(lst.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("SearchTaiSanDiChuyenNoiBo");
		return mv;
	}
	
	@RequestMapping(params="search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, Model model, @ModelAttribute("TaiSanDiChuyenNoiBoForm") TaiSanDiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv  = new ModelAndView();
		SelectDepartmentDao ex = new SelectDepartmentDao((String) request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD));
		try {
			List<DEPARTMENT_S> LISTDEPT = ex.excute();
			model.addAttribute("lstDept", new GetMap().getMapDepartment(LISTDEPT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String deptCd = request.getParameter("dept");
		mv.addObject("deptCd", deptCd);
		ASSET assCheck = new ASSET();
		assCheck.setDeptCd(deptCd);
		SelectTaiSanDao SelectTaiSanCommon = new SelectTaiSanDao(assCheck);
		try {
			List<ASSET> lstCommon = SelectTaiSanCommon.excute();
			Map<String,String> lstModel = new HashMap<String,String>();
			Map<String,String> lstSeries = new HashMap<String,String>();
			Map<String,String> lstRfid = new HashMap<String,String>();
			Map<String,String> lstName = new HashMap<String,String>();
			lstModel.put("", "");
			lstSeries.put("", "");
			lstRfid.put("", "");
			lstName.put("", "");
			if(lstCommon != null) {
				for(int i=0; i<lstCommon.size();i++) {
					lstModel.put(lstCommon.get(i).getModel(), lstCommon.get(i).getModel());
					lstSeries.put(lstCommon.get(i).getSeries(), lstCommon.get(i).getSeries());
					lstRfid.put(lstCommon.get(i).getRfid(), lstCommon.get(i).getRfid());
					lstName.put(lstCommon.get(i).getName(), lstCommon.get(i).getName());
				}
			}
			model.addAttribute("lstDept", lstModel);
			model.addAttribute("lstSeries", lstSeries);
			model.addAttribute("lstRfid", lstRfid);
			model.addAttribute("lstName", lstName);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ASSET assSearch = new ASSET();
		assSearch.setDeptCd(request.getParameter("dept"));
		assSearch.setRfid(form.getRfid());
		assSearch.setSeries(form.getSeries());
		assSearch.setModel(form.getModel());
		assSearch.setName(form.getName());
		SelectTaiSanDao selectEx = new SelectTaiSanDao(assSearch);
		try {
			List<ASSET> lst = selectEx.excute();
			if(lst.size() == 0) {
				UtilCommon.showMessageNotification(mv, "KHÔNG TÌM THẤY LỆNH NÀO");
			} else {
				UtilCommon.showMessageNotification(mv, "TÌM THẤY "+ lst.size() + " PHÙ HỢP");
				mv.addObject("lst",lst);
			}
			System.out.println(lst.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("SearchTaiSanDiChuyenNoiBo");
		return mv;
	}
	
	@RequestMapping(params="delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, Model model, @ModelAttribute("DiChuyenNoiBoForm") DiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv  = new ModelAndView();

		SelectDepartmentDao ex = new SelectDepartmentDao((String) request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD));
		try {
			List<DEPARTMENT_S> LISTDEPT = ex.excute();
			model.addAttribute("lstDept", new GetMap().getMapDepartment(LISTDEPT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LENHDICHUYENNOIBO lenhDel = new LENHDICHUYENNOIBO();
		lenhDel.setId(request.getParameter("couponId"));
		UpdateDeleteLenhDiChuyenNoiBoDao exDelete = new UpdateDeleteLenhDiChuyenNoiBoDao(lenhDel);
		try {
			exDelete.excute();
			UtilCommon.showMessageNotification(mv, "XOÁ THÀNH CÔNG LỆNH ĐIỀU ĐỘNG");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			UtilCommon.showMessageError(mv, "LỖI XOÁ LỆNH ĐIỀU ĐỘNG");
		}
		
		
		LENHDICHUYENNOIBO lenh = new LENHDICHUYENNOIBO();
		lenh.setCmpnCd((String) request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD));
		lenh.setDeptIn(form.getDeptIn());
		lenh.setDeptOut(form.getDeptOut());
		lenh.setDeptOut(form.getDeptOut());
		lenh.setDateStart(form.getDateOut());
		lenh.setNoNumber(form.getSticker());
		if("ALL".equals(form.getStatus())) {
			lenh.setStatus(null);
		} else {
			lenh.setStatus(form.getStatus());
		}
		
		
		SelectDiChuyenNoiBoDao selectEx = new SelectDiChuyenNoiBoDao(lenh);
		try {
			List<LENHDICHUYENNOIBO> lst = selectEx.excute();
			if(lst.size() == 0) {
				UtilCommon.showMessageNotification(mv, "KHÔNG TÌM THẤY LỆNH NÀO");
			} else {
				UtilCommon.showMessageNotification(mv, "TÌM THẤY "+ lst.size() + " PHÙ HỢP");
				mv.addObject("lst",lst);
			}
			System.out.println(lst.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("DiChuyenNoiBo");
		return mv;
	}
	
	
	@RequestMapping(params="TaoLenh", method = RequestMethod.POST)
	public ModelAndView TaoLenh(HttpServletRequest request, Model model) {
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/TaoLenhDiChuyenNoiBo");
		return mv;
	}
	
	@RequestMapping(params="declare", method = RequestMethod.POST)
	public ModelAndView delcare(HttpServletRequest request, Model model) {
		ModelAndView mv  = new ModelAndView();
		String couponCd = request.getParameter("couponId");
		if(UtilCommon.isEmpty(couponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			request.getSession().setAttribute(SessionParams.SESSION_COUPON_CD, couponCd);
			mv.setViewName("redirect:/KhaiBaoTaiSanLenhDiChuyenNoiBo");
		}		
		return mv;
	}
	
	@RequestMapping(params="PCD", method = RequestMethod.POST)
	public ModelAndView PCD(HttpServletRequest request, Model model) {
		ModelAndView mv  = new ModelAndView();
		String couponCd = request.getParameter("couponId");
		if(UtilCommon.isEmpty(couponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			request.getSession().setAttribute(SessionParams.SESSION_COUPON_CD, couponCd);
			mv.setViewName("redirect:/PCDTaiSanLenhDiChuyenNoiBo");
		}		
		return mv;
	}
	
	@RequestMapping(params="KT", method = RequestMethod.POST)
	public ModelAndView KT(HttpServletRequest request, Model model) {
		ModelAndView mv  = new ModelAndView();
		String couponCd = request.getParameter("couponId");
		if(UtilCommon.isEmpty(couponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			request.getSession().setAttribute(SessionParams.SESSION_COUPON_CD, couponCd);
			mv.setViewName("redirect:/KTTaiSanLenhDiChuyenNoiBo");
		}		
		return mv;
	}
	
	@RequestMapping(params="KV", method = RequestMethod.POST)
	public ModelAndView KV(HttpServletRequest request, Model model) {
		ModelAndView mv  = new ModelAndView();
		String couponCd = request.getParameter("couponId");
		if(UtilCommon.isEmpty(couponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			request.getSession().setAttribute(SessionParams.SESSION_COUPON_CD, couponCd);
			mv.setViewName("redirect:/KVTaiSanLenhDiChuyenNoiBo");
		}		
		return mv;
	}
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public ModelAndView back() {
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("redirect:/DieuDongTaiSan");
		return mv;
	}

}
