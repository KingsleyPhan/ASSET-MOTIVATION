package com.pts.motivation.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.groupdocs.conversion.internal.a.a.re;
import com.groupdocs.conversion.internal.c.a.s.ls;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.InsertLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.MoveDetailUpdateDao;
import com.pts.motivation.dao.MoveObjectCountDao;
import com.pts.motivation.dao.MoveObjectDetailInsertDao;
import com.pts.motivation.dao.MoveObjectDetailSelectDao;
import com.pts.motivation.dao.MoveObjectInsertDao;
import com.pts.motivation.dao.MoveObjectSelectDao;
import com.pts.motivation.dao.MoveObjectUpdateDao;
import com.pts.motivation.dao.MoveObjectUpdateDeleteDa;
import com.pts.motivation.dao.SelectDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.SelectTaiSanDao;
import com.pts.motivation.dao.UpdateCommentCreateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.ASSET;
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.AssetMoveObjectDetail;
import com.pts.motivation.model.LENHDICHUYENNOIBO;
import com.pts.motivation.model.MoveObject;

@Controller
@RequestMapping("PhongKeToan")
public class PhongKeToanController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		String id  = request.getParameter("moveId");
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
		mv.setViewName("PhongKeToan");
		return mv;
	}
	
	@RequestMapping(params = "approve", method=RequestMethod.POST)
	public ModelAndView approve(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject, BindingResult result) {
		
		ModelAndView mv = new ModelAndView();
		
		if("M".equals(moveObject.getCode())) {
			moveObject.setStatus("OK");
		} else {
			moveObject.setStatus("KT");
		}
		
		moveObject.setUserAccount(request.getSession().getAttribute(SessionParams.SESSION_USER_NAME).toString());
		moveObject.setDateAccount(UtilCommon.getDateCurrent("dd/MM/yyyy"));
		
		MoveObjectUpdateDao update  = new MoveObjectUpdateDao(moveObject);
		
		try {
			update.excute();
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "DUYỆT LỆNH THÀNH CÔNG");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			mv.addObject(SessionParams.MESSAGE_ERROR, "LỖI XÃY RA KHI DUYỆT LỆNH");
		}
		
		
		String id  = moveObject.getId();
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
		mv.setViewName("PhongKeToan");
		
		return mv;
	}
	
	@RequestMapping(params = "disApprove", method=RequestMethod.POST)
	public ModelAndView disApprove(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject, BindingResult result) {
		
		ModelAndView mv = new ModelAndView();
		
		moveObject.setStatus("NOT_KT");
		moveObject.setUserAccount(request.getSession().getAttribute(SessionParams.SESSION_USER_NAME).toString());
		moveObject.setDateAccount(UtilCommon.getDateCurrent("dd/MM/yyyy"));
		
		MoveObjectUpdateDao update  = new MoveObjectUpdateDao(moveObject);
		
		try {
			update.excute();
			mv.addObject(SessionParams.MESSAGE_NOTIFICATION, "DUYỆT LỆNH THÀNH CÔNG");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			mv.addObject(SessionParams.MESSAGE_ERROR, "LỖI XÃY RA KHI DUYỆT LỆNH");
		}
		
		
		String id  = moveObject.getId();
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
		mv.setViewName("PhongKeToan");
		
		return mv;
	}
	
	@RequestMapping(params = "addRow", method=RequestMethod.POST)
	public ModelAndView addRow(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		
		
		if (result.hasErrors()) {
			return mv;
		} 
		
		String numberRow = request.getParameter("numberRow");
		
		MoveObject lenh  = new MoveObject();
		lenh.setId(moveObject.getId());
		
		for(int i =1; i<=Integer.parseInt(numberRow);i++) {
			AssetMoveObjectDetail item = new AssetMoveObjectDetail();
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
				AssetMoveObjectDetail item = new AssetMoveObjectDetail();
				lenh.getLstAsset().add(item);
			}
		}
		
		mv.addObject("lstChiTiet",lenh.getLstAsset());
		mv.addObject("numberRow", numberRow);
		mv.setViewName("KhaiBaoChoMuonTrongHeThong");
		return mv;
	}
	
	
	@RequestMapping(params="back" , method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/ChoMuonTrongHeThong");
		return mv;
	}
}
