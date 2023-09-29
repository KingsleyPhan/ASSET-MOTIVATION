package com.pts.motivation.controller;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.CompanyInsertDao;
import com.pts.motivation.dao.CompanySelectDao;
import com.pts.motivation.dao.DepInsertDao;
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
		mv.addObject("param1", request.getParameter("param1"));
		mv.addObject("param2", request.getParameter("param2"));
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
	
	
	@RequestMapping(value = "/PopupCompanyInput", method = RequestMethod.POST)
	public ModelAndView PopupCompanyInputPost(HttpServletRequest request)
	{
		String TITLE = "DANH SÁCH CÁC  DOANH NGHIỆP";
		ModelAndView mv = new ModelAndView();
		String shortName = request.getParameter("shortName");
		String fullName = request.getParameter("fullName");
		
		CompanyModel comn = new CompanyModel();
		comn.setCompany_name(fullName);
		comn.setCompany_shortname(shortName);
		comn.setCompany_cd(UtilCommon.getDateCurrent("YYYYMMddHHmmSS"));
		
		CompanyInsertDao insrt = new CompanyInsertDao(comn);
		try {
			try {
				insrt.excute();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Department dept = new Department();
			dept.setCompany_cd(comn.getCompany_cd());
			dept.setDept_name("ĐƠN VỊ SỞ TẠI");
			dept.setDept_cd(UtilCommon.getDateCurrent("YYYYMMddHHmmSS"));
			
			DepInsertDao deptIns = new DepInsertDao(dept);
			deptIns.excute();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
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
		mv.setViewName("redirect:/PopupCompanyInput?param1="+request.getParameter("param1") + "&param2="+request.getParameter("param2"));
		
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
