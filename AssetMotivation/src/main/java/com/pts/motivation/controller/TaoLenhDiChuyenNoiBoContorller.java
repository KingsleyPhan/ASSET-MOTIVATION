package com.pts.motivation.controller;

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

import com.pts.motivation.common.GetMap;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.InsertTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.SelectDepartmentDao;
import com.pts.motivation.dao.SelectMotivationCountDao;
import com.pts.motivation.dao.UpdateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

@Controller
@RequestMapping("TaoLenhDiChuyenNoiBo")
public class TaoLenhDiChuyenNoiBoContorller {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		if(!model.containsAttribute("TaoLenhDiChuyenNoiBoForm")) {
			TaoLenhDiChuyenNoiBoForm TaoLenhDiChuyenNoiBoForm = new TaoLenhDiChuyenNoiBoForm();
			TaoLenhDiChuyenNoiBoForm.setId("");			
			model.addAttribute("TaoLenhDiChuyenNoiBoForm", TaoLenhDiChuyenNoiBoForm);
		}
		
		mv.setViewName("TaoLenhDiChuyenNoiBo");
		return mv;
	}
	
	
	@RequestMapping(params = "create", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, Model model, @ModelAttribute("TaoLenhDiChuyenNoiBoForm") TaoLenhDiChuyenNoiBoForm form, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		if (result.hasErrors()) {
			return mv;
		} 
		
		
		boolean isUpdate = false;
		LENHDICHUYENNOIBO LenhDiChuyenNoiBo = new LENHDICHUYENNOIBO();
		if(UtilCommon.isNotEmpty(form.getId())) {
			LenhDiChuyenNoiBo.setId(form.getId());
			LenhDiChuyenNoiBo.setUpdateDt(UtilCommon.getDateCurrent(SessionParams.CD_FULL_DATE));
			LenhDiChuyenNoiBo.setUpdateUser(UtilCommon.getSessionValue(request, SessionParams.SESSION_USER_ID));
			LenhDiChuyenNoiBo.setNoNumber(form.getNoNumber());
			LenhDiChuyenNoiBo.setSticker(form.getSticker());
			isUpdate=true;
		} else {
			LenhDiChuyenNoiBo.setId(UtilCommon.getDateCurrent(SessionParams.OBJECT_CD));
			LenhDiChuyenNoiBo.setInsertDt(UtilCommon.getDateCurrent(SessionParams.CD_FULL_DATE));
			LenhDiChuyenNoiBo.setInsertUser(UtilCommon.getSessionValue(request, SessionParams.SESSION_USER_ID));
			
			SelectMotivationCountDao exCount = new SelectMotivationCountDao(UtilCommon.getSessionValue(request, SessionParams.SESSION_CMPN_CD).toString());
			try {
				String value = exCount.excute();
				if(value != null) {
					int valueInt =Integer.parseInt(value) + 1; 
					LenhDiChuyenNoiBo.setNoNumber(valueInt+"");
				} else {
					LenhDiChuyenNoiBo.setNoNumber("---");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LenhDiChuyenNoiBo.setSticker("");
		}	
		LenhDiChuyenNoiBo.setStatus("NEW");
		LenhDiChuyenNoiBo.setCmpnCd(UtilCommon.getSessionValue(request, SessionParams.SESSION_CMPN_CD).toString());
		LenhDiChuyenNoiBo.setCmpnCdIn(UtilCommon.getSessionValue(request, SessionParams.SESSION_CMPN_CD).toString());
		LenhDiChuyenNoiBo.setCmpnCdOut(UtilCommon.getSessionValue(request, SessionParams.SESSION_CMPN_CD).toString());
		LenhDiChuyenNoiBo.setDeptIn(form.getDeptIn());
		LenhDiChuyenNoiBo.setDeptOut(form.getDeptOut());
		LenhDiChuyenNoiBo.setDateStart(form.getDateOut());
		LenhDiChuyenNoiBo.setDateEndScheduale("");
		LenhDiChuyenNoiBo.setReason(form.getReason());
		LenhDiChuyenNoiBo.setDeleteFg("0");
		
		if(isUpdate== false) {
			InsertTaoLenhDiChuyenNoiBoDao ex = new InsertTaoLenhDiChuyenNoiBoDao(LenhDiChuyenNoiBo);
			try {
				ex.excute();
				form.setId(LenhDiChuyenNoiBo.getId());
				form.setNoNumber(LenhDiChuyenNoiBo.getNoNumber());
				form.setSticker(LenhDiChuyenNoiBo.getSticker());
				UtilCommon.showMessageNotification(mv, "TẠO LỆNH THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				UtilCommon.showMessageError(mv, e.toString());
			}
		} else {
			UpdateTaoLenhDiChuyenNoiBoDao ex = new UpdateTaoLenhDiChuyenNoiBoDao(LenhDiChuyenNoiBo);
			try {
				ex.excute();
				form.setId(LenhDiChuyenNoiBo.getId());
				form.setNoNumber(LenhDiChuyenNoiBo.getNoNumber());
				form.setSticker(LenhDiChuyenNoiBo.getSticker());
				UtilCommon.showMessageNotification(mv, "CẬP NHẬT LỆNH THÀNH CÔNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				UtilCommon.showMessageError(mv, e.toString());
			}
		}
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
