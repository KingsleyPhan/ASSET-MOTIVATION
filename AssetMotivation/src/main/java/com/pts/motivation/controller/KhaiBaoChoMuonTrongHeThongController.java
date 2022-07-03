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
@RequestMapping("KhaiBaoChoMuonTrongHeThong")
public class KhaiBaoChoMuonTrongHeThongController {
	
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
		mv.setViewName("KhaiBaoChoMuonTrongHeThong");
		return mv;
	}
	
	@RequestMapping(params = "save", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		
		String numberRow = request.getParameter("numberRow");
		
		
		
		
		for(int i =1; i<=Integer.parseInt(numberRow);i++) {
			AssetMoveObjectDetail item = new AssetMoveObjectDetail();
			item.setIdMove(request.getParameter("idMove_"+i));
			item.setRfid(request.getParameter("rfid_"+i));
			item.setName(request.getParameter("name_"+i));
			item.setModel(request.getParameter("model_"+i));
			item.setSeries(request.getParameter("series_"+i));
			item.setAssesseries(request.getParameter("assesseries_"+i));
			moveObject.getLstAsset().add(item);
		}
		
		//Loc du lieu
		String message = "";
		for(int i =0;i<moveObject.getLstAsset().size();i++) {
			AssetMoveObjectDetail item  = moveObject.getLstAsset().get(i);
			
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
				assetCheck.setDeptCd(moveObject.getDeptOutId());
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
			for(int i =0;i<moveObject.getLstAsset().size();i++) {
				int row = i+1;
				AssetMoveObjectDetail item  = moveObject.getLstAsset().get(i);
				if(UtilCommon.isEmpty(item.getIdMove()) == false) {
					//Cap nhatetidd
					MoveDetailUpdateDao updateEx = new MoveDetailUpdateDao(item,moveObject.getId());
					try {
						updateEx.excute();						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						messageData += "Lỗi cập nhật dữ liệu tài sản dòng: " + row + "</br>";
					}
					notification += "Cập nhật dữ liệu tài sản của lệnh thành công</br>";
				} else {
					//Them
					if(isEmptyAsset(item) == false) {
					item.setId(UtilCommon.getDateCurrent(SessionParams.OBJECT_CD));
					item.setIdMove(moveObject.getId());
					MoveObjectDetailInsertDao insertEX = new MoveObjectDetailInsertDao(item);
					try {
						insertEX.excute();						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						messageData += "Lỗi thêm dữ liệu tài sản dòng: " + row + "</br>";
					}
					notification += "Thêm dữ liệu tài sản của lệnh thành công</br>";
					}
				}
			} 
			if(UtilCommon.isEmpty(messageData) == false) {
				mv.addObject(SessionParams.MESSAGE_ERROR, messageData);
			}
			if(UtilCommon.isEmpty(notification) == false) {
				mv.addObject(SessionParams.MESSAGE_NOTIFICATION, notification);
			}		
			
			MoveObjectUpdateDao Excommnent = new MoveObjectUpdateDao(moveObject);
			try {
				Excommnent.excute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			mv.addObject(SessionParams.MESSAGE_ERROR, message);
		}
		
		mv.addObject("lstChiTiet", moveObject.getLstAsset());
		mv.addObject("numberRow",  moveObject.getLstAsset().size());
		
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
	
	public Boolean isEmptyAsset(AssetMoveObjectDetail item) {
		if(UtilCommon.isEmpty(item.getRfid())
				&& UtilCommon.isEmpty(item.getName())
				&& UtilCommon.isEmpty(item.getModel())
				&& UtilCommon.isEmpty(item.getSeries())
				&& UtilCommon.isEmpty(item.getAssesseries())) {
			return true;
		} else {
			return false;
		}
	}
}
