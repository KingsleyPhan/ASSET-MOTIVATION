package com.pts.motivation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.Department;




public class DepartmentPermissionSelectDao {
	Department department;
	String userCd;
	
	public DepartmentPermissionSelectDao()
	{
		
	}
	

	public DepartmentPermissionSelectDao(Department department, String user_cd)
	{
		this.department = department;
		this.userCd = user_cd;
	}

	public List<Department> excute() throws SQLException
	{
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		Statement stmt = connectString.createStatement();
		ResultSet result = null;
		System.out.println(getSql());
		result = stmt.executeQuery(getSql());
		List<Department> lstDept =new ArrayList<Department>();
		while (result.next()) {
			Department dept = new Department();
			dept.setCompany_cd(result.getString("CMPN_CD"));
			dept.setDept_cd(result.getString("DEPT_CD"));
			dept.setDept_name(result.getString("DEPARTMENT_NAME"));
			dept.setDept_desciption(result.getString("DESCRIPTION"));
			
			lstDept.add(dept);
		}
		return lstDept;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT 	DPT.DEPT_CD, DPT.CMPN_CD, DPT.DEPARTMENT_NAME, DPT.DESCRIPTION	");
		sql.append(" FROM		");
		sql.append(" 	DEPRATMENT DPT ");

		sql.append("WHERE 	 	1=1 ");
		if(department != null)
		{
			if(department.getCompany_cd()!=null && department.getCompany_cd().trim().length()>0)
			{
				sql.append(" 	AND  DPT.CMPN_CD = ").append("'" + department.getCompany_cd() +"'");
			}
		}
		
		return sql.toString();
	}
}
