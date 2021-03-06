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
			if("TM".equals(mode)) {
				moveObject.setCode("TM");
			}
			if("BG".equals(mode)) {
				moveObject.setCode("BG");
			}
			if("TTH".equals(mode)) {
				moveObject.setCode("TTH");
			}
			if("TH".equals(mode)) {
				moveObject.setCode("TH");
				moveObject.setCmpnOutId("");
				moveObject.setCmpnOutName("");
			}
			if("0".equals(mode)) {
				moveObject.setCode("");
				moveObject.setCmpnInId(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString());
				moveObject.setCmpnInName(request.getSession().getAttribute(SessionParams.SESSION_CMPN_NAME).toString());
			}
			
			
			moveObject.setCmpnCd(request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString());
			moveObject.setStatus("NEW");
			moveObject.setDeleteFg("0");
			
			MoveObjectCountDao countSelect = new MoveObjectCountDao(moveObject.getCmpnCd());
			
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
			message += "Vui l??ng ch???n ????n v??? nh???p <br>";
		}
		if(UtilCommon.isEmpty(moveObject.getDeptOutId())) {
			message += "Vui l??ng ch???n ????n v??? xu???t <br>";
		}
		if(UtilCommon.isDateInputRight(moveObject.getDateOut()) == false) {
			message += "Vui l??ng nh???p ng??y xu???t theo ?????nh d???ng dd/mm/yyyy <br>";
		}
		if(!"".equals(moveObject.getCode()) && !"TTH".equals(moveObject.getCode()) && !"TM".equals(moveObject.getCode()) && !"BG".equals(moveObject.getCode()) && UtilCommon.isDateInputRight(moveObject.getDateIn()) == false) {
			message += "Vui l??ng nh???p ng??y tr??? theo ?????nh d???ng dd/mm/yyyy <br>";
		}
		if(UtilCommon.isEmpty(moveObject.getCmpnInId())) {
			message += "Vui l??ng ch???n c??ng ty nh???p <br>";
		}
		if(UtilCommon.isEmpty(moveObject.getReason())) {
			message += "Vui l??ng nh???p l?? do ??i???u ?????ng <br>";
		}
		
		if(UtilCommon.isEmpty(message)) {
			
			moveObject.setUserCreate(request.getSession().getAttribute(SessionParams.SESSION_USER_NAME)+"");
			moveObject.setDateCreate(UtilCommon.getDateCurrent("dd/MM/yyyy HH:mm:ss"));
			
			if(UtilCommon.isEmpty(moveObject.getId())) {
				try {
					//-------------------------------
					MoveObjectCountDao countSelect = new MoveObjectCountDao(moveObject.getCmpnCd());
					
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
					//-------------------------------
					moveObject.setId(UtilCommon.getDateCurrent(SessionParams.OBJECT_CD));
					MoveObjectInsertDao insert = new MoveObjectInsertDao(moveObject);		
					insert.excute();
					mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "L??U L???NH TH??NH C??NG");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(SessionParams.MESSAGE_ERROR, "X??y ra l???i qu?? tr??nh l??u l???nh");
				}
			} else {
				try {
					MoveObjectUpdateDao update = new MoveObjectUpdateDao(moveObject);		
					update.excute();
					mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "CH???NH S???A L???NH L???NH TH??NH C??NG");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mv.addObject(SessionParams.MESSAGE_ERROR, "X??y ra l???i qu?? tr??nh ch???nh s???a l???nh");
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
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "X??A TH??NH C??NG L???NH");
			mv.setViewName("redirect:/ChoMuonTrongHeThong");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject(SessionParams.MESSAGE_ERROR, "X??Y RA L???I KHI X??A L???NH");
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
