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

import com.groupdocs.conversion.internal.a.a.re;
import com.groupdocs.conversion.internal.c.a.s.ls;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.MoveObjectCountDao;
import com.pts.motivation.dao.MoveObjectInsertDao;
import com.pts.motivation.dao.MoveObjectSelectDao;
import com.pts.motivation.dao.MoveObjectUpdateDao;
import com.pts.motivation.dao.MoveObjectUpdateDeleteDa;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.MoveObject;

@Controller
@RequestMapping("TaoLenhChoMuonTrongHeThong")
public class TaoLenhChoMuonTrongHeThongController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		String id  = request.getParameter("moveId");
		if(UtilCommon.isEmpty(id) == false) {
			MoveObject moveOb = new MoveObject();
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
		if(!model.containsAttribute("moveObject")) {
			
			MoveObject moveObject = new MoveObject();
			moveObject.setCmpnOutId(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString());
			moveObject.setCmpnOutName(request.getSession().getAttribute(SessionParams.SESSION_CMPN_NAME).toString());
		
			String mode = request.getParameter("mode");
			if("CM".equals(mode)) {
				moveObject.setCode("CM");
			}
			if("0".equals(mode)) {
				moveObject.setCode("");
				moveObject.setCmpnInId(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString());
				moveObject.setCmpnInName(request.getSession().getAttribute(SessionParams.SESSION_CMPN_NAME).toString());
			}
			
			
			moveObject.setCmpnCd(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString());
			moveObject.setStatus("NEW");
			moveObject.setDeleteFg("0");
			
			MoveObjectCountDao countSelect = new MoveObjectCountDao(moveObject.getCmpnOutId());
			
			try {
				String count = countSelect.excute();
				if(count !=null) {
					if("0".equals(count)) {
						moveObject.setNo("1");
					} else {
						moveObject.setNo((Integer.parseInt(count) + 1) + "");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.addAttribute("moveObject", moveObject);
		}
		
		mv.setViewName("TaoLenhChoMuonTrongHeThong");
		return mv;
	}
	
	
	@RequestMapping(params="save" , method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		ModelAndView mv = new ModelAndView();
		
		String message="";
		
		if(UtilCommon.isEmpty(moveObject.getDeptInId())) {
			message += "Vui lòng chọn đơn vị nhập <br>";
		}
		if(UtilCommon.isEmpty(moveObject.getDeptOutId())) {
			message += "Vui lòng chọn đơn vị xuất <br>";
		}
		if(UtilCommon.isDateInputRight(moveObject.getDateOut()) == false) {
			message += "Vui lòng nhập ngày xuất theo định dạng dd/mm/yyyy <br>";
		}
		if(UtilCommon.isDateInputRight(moveObject.getDateIn()) == false) {
			message += "Vui lòng nhập ngày nhập theo định dạng dd/mm/yyyy <br>";
		}
		if(UtilCommon.isEmpty(moveObject.getCmpnInId())) {
			message += "Vui lòng chọn công ty nhập <br>";
		}
		if(UtilCommon.isEmpty(moveObject.getReason())) {
			message += "Vui lòng nhập lý do điều động <br>";
		}
		
		if(UtilCommon.isEmpty(message)) {
			
			moveObject.setUserCreate(request.getSession().getAttribute(SessionParams.SESSION_USER_NAME)+"");
			moveObject.setDateCreate(UtilCommon.getDateCurrent("dd/MM/yyyy"));
			
			if(UtilCommon.isEmpty(moveObject.getId())) {
				try {
					moveObject.setId(UtilCommon.getDateCurrent(SessionParams.OBJECT_CD));
					MoveObjectInsertDao insert = new MoveObjectInsertDao(moveObject);		
					insert.excute();
					mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "LƯU LỆNH THÀNH CÔNG");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(SessionParams.MESSAGE_ERROR, "Xãy ra lỗi quá trình lưu lệnh");
				}
			} else {
				try {
					MoveObjectUpdateDao update = new MoveObjectUpdateDao(moveObject);		
					update.excute();
					mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "CHỈNH SỬA LỆNH LỆNH THÀNH CÔNG");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(SessionParams.MESSAGE_ERROR, "Xãy ra lỗi quá trình chỉnh sửa lệnh");
				}
			}
			
		} else {
			mv.addObject(SessionParams.MESSAGE_ERROR, message);
		}
		
		mv.setViewName("TaoLenhChoMuonTrongHeThong");
		return mv;
	}
	
	@RequestMapping(params="delete" , method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		ModelAndView mv = new ModelAndView();
		MoveObjectUpdateDeleteDa updateDel = new MoveObjectUpdateDeleteDa(moveObject);
		try {
			updateDel.excute();
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "XÓA THÀNH CÔNG LỆNH");
			mv.setViewName("redirect:/ChoMuonTrongHeThong");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(SessionParams.MESSAGE_ERROR, "XÃY RA LỖI KHI XÓA LỆNH");
			mv.setViewName("TaoLenhChoMuonTrongHeThong");
		}
		
		return mv;
	}
	
	@RequestMapping(params="back" , method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/ChoMuonTrongHeThong");
		return mv;
	}
}
