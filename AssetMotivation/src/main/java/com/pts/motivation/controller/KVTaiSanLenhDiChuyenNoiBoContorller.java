package com.pts.motivation.controller;

import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.pts.motivation.common.GetMap;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.InsertLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.InsertTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.SelectDepartmentDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoDao;
import com.pts.motivation.dao.SelectMotivationCountDao;
import com.pts.motivation.dao.UpdateApproveLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateCommentCreateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateDisApproveLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateDonViTaiSanLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.UpdateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

@Controller
@RequestMapping("KVTaiSanLenhDiChuyenNoiBo")
public class KVTaiSanLenhDiChuyenNoiBoContorller {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		String CouponCd = (String) request.getSession().getAttribute(SessionParams.SESSION_COUPON_CD);
		String numberRow = "0";
		if(UtilCommon.isEmpty(CouponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			LENHDICHUYENNOIBO lenh = new LENHDICHUYENNOIBO();
			lenh.setId(CouponCd);
			SelectDiChuyenNoiBoDao ex = new SelectDiChuyenNoiBoDao(lenh);
			
			try {
				List<LENHDICHUYENNOIBO> lst = ex.excute();
				if(lst.size()==1) {
					TaoLenhDiChuyenNoiBoForm TaoLenhDiChuyenNoiBoForm = new TaoLenhDiChuyenNoiBoForm();
					TaoLenhDiChuyenNoiBoForm.setId(lst.get(0).getId().trim());		
					TaoLenhDiChuyenNoiBoForm.setNoNumber(lst.get(0).getNoNumber());
					TaoLenhDiChuyenNoiBoForm.setSticker(lst.get(0).getSticker());
					TaoLenhDiChuyenNoiBoForm.setDeptIn(lst.get(0).getDeptIn().trim());
					TaoLenhDiChuyenNoiBoForm.setDeptOut(lst.get(0).getDeptOut().trim());
					TaoLenhDiChuyenNoiBoForm.setDateOut(lst.get(0).getDateStart().trim());
					TaoLenhDiChuyenNoiBoForm.setReason(lst.get(0).getReason());
					TaoLenhDiChuyenNoiBoForm.setCommentCreate(lst.get(0).getCommentCreate());
					TaoLenhDiChuyenNoiBoForm.setCommentPCD(lst.get(0).getCommentPCD());
					TaoLenhDiChuyenNoiBoForm.setCommentAccount(lst.get(0).getCommentAccount());
					TaoLenhDiChuyenNoiBoForm.setCommentStock(lst.get(0).getCommentStock());
					model.addAttribute("TaoLenhDiChuyenNoiBoForm", TaoLenhDiChuyenNoiBoForm);
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
			
				
				
				
				
				mv.setViewName("KVTaiSanLenhDiChuyenNoiBo");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return mv;
	}
	
	
	@RequestMapping(params = "approve", method=RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request, Model model, @ModelAttribute("TaoLenhDiChuyenNoiBoForm") TaoLenhDiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		if (result.hasErrors()) {
			return mv;
		} 
		
		
		LENHDICHUYENNOIBO lenh  = new LENHDICHUYENNOIBO();
		lenh.setId(form.getId());
		lenh.setCommentStock(form.getCommentStock());
		lenh.setApproveStockDt(UtilCommon.getDateCurrent("dd/MM/yyyy"));
		lenh.setApproveStockUser((String) request.getSession().getAttribute(SessionParams.SESSION_USER_NAME));
		lenh.setDeptIn(form.getDeptIn());
		
		UpdateApproveLenhDiChuyenNoiBoDao exUpdate = new UpdateApproveLenhDiChuyenNoiBoDao(lenh);
		try {
			exUpdate.excuteKV();
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "DUYỆT THÀNH CÔNG");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			mv.addObject(SessionParams.MESSAGE_ERROR, "DUYỆT KHÔNG THÀNH CÔNG");
		}
		
		SelectDiChuyenNoiBoChiTietDao exAsset = new SelectDiChuyenNoiBoChiTietDao(lenh);
		String numberRow =  "";
		LENHDICHUYENNOIBO lenhChiTiet = null;
		try {
			lenhChiTiet = exAsset.excute();
			
			if(lenhChiTiet.getLstAsset().size()==0) {
				numberRow= "1";
			} else {
				numberRow = lenhChiTiet.getLstAsset().size()+"";
			}
			
			for(int i =0;i<lenhChiTiet.getLstAsset().size();i++) {
				ASSETDICHUYENNOIBO assetDiChuyen = new ASSETDICHUYENNOIBO();
				assetDiChuyen.setDeptCd(lenh.getDeptIn());
				assetDiChuyen.setRfid(lenhChiTiet.getLstAsset().get(0).getRfid());
				UpdateDonViTaiSanLenhDiChuyenNoiBoDao exUpdateDonVi = new UpdateDonViTaiSanLenhDiChuyenNoiBoDao(assetDiChuyen);
				exUpdateDonVi.excute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
		mv.addObject("numberRow", numberRow);
		
		return mv;
	}
	
	@RequestMapping(params = "disApprove", method=RequestMethod.POST)
	public ModelAndView disApprove(HttpServletRequest request, Model model, @ModelAttribute("TaoLenhDiChuyenNoiBoForm") TaoLenhDiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		if (result.hasErrors()) {
			return mv;
		} 
		
		
		LENHDICHUYENNOIBO lenh  = new LENHDICHUYENNOIBO();
		lenh.setId(form.getId());
		lenh.setCommentStock(form.getCommentStock());
		lenh.setApproveStockDt(UtilCommon.getDateCurrent("dd/MM/yyyy"));
		lenh.setApproveStockUser((String) request.getSession().getAttribute(SessionParams.SESSION_USER_NAME));
		
		UpdateDisApproveLenhDiChuyenNoiBoDao exUpdate = new UpdateDisApproveLenhDiChuyenNoiBoDao(lenh);
		try {
			exUpdate.excuteDisKV();
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "CẬP NHẬT KHÔNG DUYỆT THÀNH CÔNG");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			mv.addObject(SessionParams.MESSAGE_ERROR, "CẬP NHẬT KHÔNG DUYỆT KHÔNG THÀNH CÔNG");
		}
		
		SelectDiChuyenNoiBoChiTietDao exAsset = new SelectDiChuyenNoiBoChiTietDao(lenh);
		String numberRow =  "";
		LENHDICHUYENNOIBO lenhChiTiet = null;
		try {
			lenhChiTiet = exAsset.excute();
			
			if(lenhChiTiet.getLstAsset().size()==0) {
				numberRow= "1";
			} else {
				numberRow = lenhChiTiet.getLstAsset().size()+"";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
		mv.addObject("numberRow", numberRow);
		
		return mv;
	}
	
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public String back() {
		return "redirect:/DiChuyenNoiBo";
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
