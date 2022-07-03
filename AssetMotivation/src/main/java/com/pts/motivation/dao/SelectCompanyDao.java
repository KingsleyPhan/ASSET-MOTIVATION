package com.pts.motivation.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.COMPANY;

public class SelectCompanyDao {
		public SelectCompanyDao() {
			
		}
		
		public List<COMPANY> excute() throws SQLException {
			List<COMPANY> lst = new ArrayList<COMPANY>();
			DatabaseConnection conn = new DatabaseConnection();
			Statement stm = conn.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(getSQL());
			while(rs.next()) {
				COMPANY item = new COMPANY();
				item.setId(rs.getString("CMPN_CD"));
				item.setName(rs.getString("CMPN_NAME"));
				item.setShortName(rs.getString("CMPN_SHORT_NAME"));
				lst.add(item);
			}
			
			return lst;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM ");
			sql.append("COMPANY	");
			sql.append("WHERE ");
			sql.append("DELETE_FG = 0");
			return sql.toString();
		}
}
