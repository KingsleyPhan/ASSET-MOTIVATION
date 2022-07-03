package com.pts.motivation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.COMPANY;
import com.pts.motivation.model.DEPARTMENT_S;

public class SelectDepartmentDao {
		String cmpnCd; 
		public SelectDepartmentDao(String cmpnCd) {
			this.cmpnCd = cmpnCd;
		}
		
		public List<DEPARTMENT_S> excute() throws SQLException {
			List<DEPARTMENT_S> lst = new ArrayList<DEPARTMENT_S>();
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			pstm.setString(1,this.cmpnCd);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				DEPARTMENT_S item = new DEPARTMENT_S();
				item.setId(rs.getString("DEPT_CD"));
				item.setName(rs.getString("DEPARTMENT_NAME"));
				item.setCmpnCd(rs.getString("CMPN_CD"));
				lst.add(item);
			}
			
			return lst;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM ");
			sql.append("DEPRATMENT	");
			sql.append("WHERE ");
			sql.append("CMPN_CD	= ?");
			return sql.toString();
		}
}
