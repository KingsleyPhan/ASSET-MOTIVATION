package com.pts.motivation.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pts.motivation.common.SessionParams;
import com.pts.motivation.dao.BaoCaoNoiBoSelectDao;
import com.pts.motivation.dao.DepartmentPermissionSelectDao;
import com.pts.motivation.model.BaoCaoNoiBoDetail;
import com.pts.motivation.model.Department;

@Controller
@RequestMapping("BaoCaoNoiBo")
public class BaoCaoNoiBoController {
	
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String cmpnCd = request.getSession().getAttribute(SessionParams.SESSION_CMPN_CD).toString();
		BaoCaoNoiBoDetail BaoCao = new BaoCaoNoiBoDetail();
		BaoCao.setCmpnCd(cmpnCd);
		
		BaoCaoNoiBoSelectDao selectBaoCao = new BaoCaoNoiBoSelectDao(BaoCao);
		
		try {
			List<BaoCaoNoiBoDetail> lstBaoCao = selectBaoCao.excute();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

}
