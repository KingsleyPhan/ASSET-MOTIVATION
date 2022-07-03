package com.pts.motivation.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pts.motivation.common.SessionParams;
import com.pts.motivation.dao.SelectUserDao;
import com.pts.motivation.model.USER;

@Controller
@RequestMapping("DangNhap")
public class LoginController {
		
	@RequestMapping(method = RequestMethod.GET)
	public String init() {
		return "DangNhap";
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "login")
	public ModelAndView DangNhap(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		USER user = new USER(); 
		user.setUsn(request.getParameter("usn"));
		user.setPass(request.getParameter("pwd"));
		SelectUserDao ex = new SelectUserDao(user);
		try {
			List<USER> lstUser = ex.excute();
			if(lstUser != null && lstUser.size()==1) {
				request.getSession().setAttribute(SessionParams.SESSION_CMPN_CD, lstUser.get(0).getCmpnCd());
				request.getSession().setAttribute(SessionParams.SESSION_CMPN_NAME, lstUser.get(0).getCmpnName());
				request.getSession().setAttribute(SessionParams.SESSION_USER_ID, lstUser.get(0).getId());
				request.getSession().setAttribute(SessionParams.SESSION_USER_NAME, lstUser.get(0).getFullName());
				request.getSession().setAttribute(SessionParams.SESSION_USER_CD, user.getUsn());
				mv.setViewName("redirect:/DieuDongTaiSan");
			} else {
				mv.setViewName("DangNhap");
				mv.addObject("error", "THÔNG TIN TÀI KHOẢN KHÔNG CHÍNH XÁC");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("DangNhap");
			mv.addObject("error", "XẢY RA LỖI HỆ THỐNG VUI LÒNG LIÊN HỆ QUẢN TRỊ VIÊN");
		}
		return mv;
	}
}
