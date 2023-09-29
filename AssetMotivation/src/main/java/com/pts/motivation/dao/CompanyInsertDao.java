package com.pts.motivation.dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.model.CompanyModel;

public class CompanyInsertDao {
	CompanyModel form =new CompanyModel();
	public CompanyInsertDao(CompanyModel form)
	{
		this.form = form;
	}
	
	public int excute() throws SQLException, UnknownHostException
	{
		int result = 0;
		
		DatabaseConnection conn = new DatabaseConnection();
		Connection connectString = conn.getConnection();
		PreparedStatement sqlStatement = connectString.prepareStatement(getSql());
		//System.out.println(getSql());
			sqlStatement.setString(1,form.getCompany_cd());//COMPANY_CD
			sqlStatement.setString(2,form.getCompany_name());//COMPANY_NAME
			sqlStatement.setString(3,form.getCompany_shortname());//COMPANY_ADDRESS
			sqlStatement.setString(4,"0");//COMPANY_LOGO
			
			result = sqlStatement.executeUpdate();
		
		
		return result;
	}
	
	public String getSql()
	{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" INSERT INTO");
		sql.append(" COMPANY");
		sql.append(" (");
		sql.append(" 	CMPN_CD");
		sql.append(" 	,CMPN_NAME");
		sql.append(" 	,CMPN_SHORT_NAME");
		sql.append(" 	,DELETE_FG ");
		sql.append(" )");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append(" 	 ?");//COMPANY_CD
		sql.append(" 	,?");//COMPANY_NAME
		sql.append(" 	,?");//COMPANY_ADDRESS
		sql.append(" 	,?");//COMPANY_LOGO
		sql.append(" )");
		
		return sql.toString();
	}

}