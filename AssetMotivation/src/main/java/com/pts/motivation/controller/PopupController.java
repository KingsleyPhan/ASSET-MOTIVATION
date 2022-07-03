package com.pts.motivation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pts.motivation.common.SessionParams;
import com.pts.motivation.dao.CompanySelectDao;
import com.pts.motivation.dao.DepartmentPermissionSelectDao;
import com.pts.motivation.model.CompanyModel;
import com.pts.motivation.model.Department;


@Controller
public class PopupController {
	
	@RequestMapping("/PopupCompanyInput")
	public ModelAndView PopupCompanyInput(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC  DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();
		mv.addObject("TITLESCREEN", TITLE);
		CompanyModel cpm = new CompanyModel();
		cpm.setCompany_delete("0");
		CompanySelectDao cmpny = new CompanySelectDao(cpm);
		List<CompanyModel> lstCmpn = new ArrayList<CompanyModel>();
		
		try {
			lstCmpn = cmpny.excute();
			if(lstCmpn.size()>0)
			{
				mv.addObject("lst", lstCmpn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		mv.setViewName("PopupCompanyInput");
		
		return mv;
	}
	
	
	@RequestMapping("/GetListDepartment")
	public ModelAndView GetListDepartment(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC ĐƠN VỊ TRONG DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();

		Department dept = new Department();
		String cmpnd = request.getParameter("param3");
		
		dept.setCompany_cd(cmpnd);
		String userCd = (String) request.getSession().getAttribute(SessionParams.SESSION_USER_CD);
		
		DepartmentPermissionSelectDao departmentSelectDao = new DepartmentPermissionSelectDao(dept,userCd);
		
		List<Department> lstDept = new ArrayList<Department>();
		
		try {
			lstDept = departmentSelectDao.excute();
			//mv.addObject(ParamsConstants.MESSAGE_NOTIFICATION, "TÌM THẤY " + lstDept.size() + " ĐƠN VỊ");
			if(lstDept.size()>0)
			{
				mv.addObject("lst", lstDept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//mv.addObject(ParamsConstants.MESSAGE_ERROR, e.toString());
		}
		mv.setViewName("PopupGetListDepartment");
		
		return mv;
	}
}
