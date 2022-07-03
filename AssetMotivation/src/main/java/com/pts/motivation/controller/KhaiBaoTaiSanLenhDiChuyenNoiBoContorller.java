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
import com.pts.motivation.dao.SelectTaiSanDao;
import com.pts.motivation.dao.UpdateCommentCreateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.UpdateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.ASSET;
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

@Controller
@RequestMapping("KhaiBaoTaiSanLenhDiChuyenNoiBo")
public class KhaiBaoTaiSanLenhDiChuyenNoiBoContorller {
	
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
			
				
				
				
				
				mv.setViewName("KhaiBaoTaiSanLenhDiChuyenNoiBo");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return mv;
	}
	
	@RequestMapping(params = "addRow", method=RequestMethod.POST)
	public ModelAndView addRow(HttpServletRequest request, Model model, @ModelAttribute("TaoLenhDiChuyenNoiBoForm") TaoLenhDiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		if (result.hasErrors()) {
			return mv;
		} 
		
		String numberRow = request.getParameter("numberRow");
		
		LENHDICHUYENNOIBO lenh  = new LENHDICHUYENNOIBO();
		lenh.setId(form.getId());
		
		for(int i =1; i<=Integer.parseInt(numberRow);i++) {
			ASSETDICHUYENNOIBO item = new ASSETDICHUYENNOIBO();
			item.setIdMove(request.getParameter("idMove_"+i));
			item.setRfid(request.getParameter("rfid_"+i));
			item.setName(request.getParameter("name_"+i));
			item.setModel(request.getParameter("model_"+i));
			item.setSeries(request.getParameter("series_"+i));
			item.setAssesseries(request.getParameter("assesseries_"+i));
			lenh.getLstAsset().add(item);
		}
		
		if(Integer.parseInt(numberRow) > lenh.getLstAsset().size()) {
			for(int i = lenh.getLstAsset().size(); i< Integer.parseInt(numberRow) ; i++) {
				ASSETDICHUYENNOIBO item = new ASSETDICHUYENNOIBO();
				lenh.getLstAsset().add(item);
			}
		}
		
		mv.addObject("lstChiTiet",lenh.getLstAsset());
		mv.addObject("numberRow", numberRow);
		
		return mv;
	}
	
	@RequestMapping(params = "save", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, Model model, @ModelAttribute("TaoLenhDiChuyenNoiBoForm") TaoLenhDiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		if (result.hasErrors()) {
			return mv;
		} 
		
		String numberRow = request.getParameter("numberRow");
		
		LENHDICHUYENNOIBO lenh  = new LENHDICHUYENNOIBO();
		lenh.setId(form.getId());
		lenh.setCommentCreate(form.getCommentCreate());
		
		
		for(int i =1; i<=Integer.parseInt(numberRow);i++) {
			ASSETDICHUYENNOIBO item = new ASSETDICHUYENNOIBO();
			item.setIdMove(request.getParameter("idMove_"+i));
			item.setRfid(request.getParameter("rfid_"+i));
			item.setName(request.getParameter("name_"+i));
			item.setModel(request.getParameter("model_"+i));
			item.setSeries(request.getParameter("series_"+i));
			item.setAssesseries(request.getParameter("assesseries_"+i));
			lenh.getLstAsset().add(item);
		}
		
		//Loc du lieu
		String message = "";
		for(int i =0;i<lenh.getLstAsset().size();i++) {
			ASSETDICHUYENNOIBO item  = lenh.getLstAsset().get(i);
			
			if(	   UtilCommon.isEmpty(item.getRfid()) 	|| 	UtilCommon.isEmpty(item.getName())
				|| UtilCommon.isEmpty(item.getModel()) 	|| UtilCommon.isEmpty(item.getSeries()) ) 
			{
				if(UtilCommon.isEmpty(item.getRfid())
					&& UtilCommon.isEmpty(item.getName())
					&& UtilCommon.isEmpty(item.getModel())
					&& UtilCommon.isEmpty(item.getSeries())
					&& UtilCommon.isEmpty(item.getAssesseries())) {
					
				} else {
						int row = i+1;
						message += "Vui lòng nhập thông tin đầy đủ dòng: "+ row + "</br>";
				}
			} else {
				//Kiem tra co ton tai tai san o don vi hay khong
				ASSET assetCheck  = new ASSET();
				assetCheck.setDeptCd(form.getDeptOut());
				assetCheck.setRfid(item.getRfid());
				int row = i+1;
				SelectTaiSanDao exSelect = new  SelectTaiSanDao(assetCheck);
				try {
					List<ASSET> lstCheck = exSelect.excute();
					if(lstCheck.size()==0) {
						message += "Không tìm thấy tài sản dòng: " + row + "</br>";
					} else {
						if(lstCheck.size() != 1) {
							message += "Không tìm thấy nhiều hơn 1 tài sản tại dòng: " + row + "</br>";
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
		//Lu du lieu
		if(UtilCommon.isEmpty(message)) {
			String notification = "";
			String messageData = "";
			for(int i =0;i<lenh.getLstAsset().size();i++) {
				int row = i+1;
				ASSETDICHUYENNOIBO item  = lenh.getLstAsset().get(i);
				if(UtilCommon.isEmpty(item.getIdMove()) == false) {
					//Cap nhatetidd
					UpdateLenhDiChuyenNoiBoChiTietDao updateEx = new UpdateLenhDiChuyenNoiBoChiTietDao(item,lenh.getId());
					try {
						updateEx.excute();
						notification += "Cập nhật dữ liệu tài sản dòng: " + row + "</br>";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						messageData += "Lỗi cập nhật dữ liệu tài sản dòng: " + row + "</br>";
					}
				} else {
					//Them
					item.setIdMove(UtilCommon.getDateCurrent(SessionParams.OBJECT_CD));
					InsertLenhDiChuyenNoiBoChiTietDao insertEX = new InsertLenhDiChuyenNoiBoChiTietDao(item,lenh.getId());
					try {
						insertEX.excute();
						notification += "Thêm dữ liệu tài sản dòng: " + row + "</br>";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						messageData += "Lỗi thêm dữ liệu tài sản dòng: " + row + "</br>";
					}
				}
			} 
			mv.addObject(SessionParams.MESSAGE_ERROR, messageData);
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, notification);
			
			UpdateCommentCreateTaoLenhDiChuyenNoiBoDao Excommnent = new UpdateCommentCreateTaoLenhDiChuyenNoiBoDao(lenh);
			try {
				Excommnent.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			mv.addObject(SessionParams.MESSAGE_ERROR, message);
		}
		
		mv.addObject("lstChiTiet",lenh.getLstAsset());
		mv.addObject("numberRow", lenh.getLstAsset().size());
		
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
