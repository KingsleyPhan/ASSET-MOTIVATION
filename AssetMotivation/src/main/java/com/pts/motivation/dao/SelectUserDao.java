package com.pts.motivation.dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.USER;

public class SelectUserDao {
	private USER user; 
	
	public SelectUserDao(USER user) {
		this.user = user;
	}
	
	public List<USER> excute() throws SQLException, UnknownHostException {
		List<USER> lst = new ArrayList<USER>();
		DatabaseConnection conn = new  DatabaseConnection();
		Connection connEx = conn.getConnection();
		Statement stm = connEx.createStatement();
	
		ResultSet rs = stm.executeQuery(getSQL());
		
		while(rs.next()) {
			USER item = new USER();
			item.setFullName(rs.getString("USER_NAME"));
			item.setCmpnCd(rs.getString("CMPN_CD"));
			item.setId(rs.getString("USER_ID"));
			item.setCmpnName(rs.getString("CMPN_NAME"));
			lst.add(item);
		}
		return lst;
	}
	
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT US.USER_ID, US.USER_NAME, US.USER_EMPLOYEE_CD, US.CMPN_CD, CMPN.CMPN_NAME ");
		sql.append(" FROM ");
		sql.append(" USER_SYSTEM US  INNER JOIN COMPANY CMPN ON US.CMPN_CD = CMPN.CMPN_CD ");
		sql.append(" WHERE ");
		sql.append(" US.USER_EMPLOYEE_CD =  ").append("'" +this.user.getUsn()+"'");
		sql.append(" AND US.USER_PASSWORD = ").append("'" +this.user.getPass()+"'");
		
		System.out.println(sql.toString());
		
		return sql.toString();
	}
}
