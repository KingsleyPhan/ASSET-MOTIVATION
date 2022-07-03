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

public class SelectSystemControl {
		String cmpnCd; 
		String name; 
		public SelectSystemControl(String cmpnCd, String name) {
			this.cmpnCd = cmpnCd;
			this.name = name;
		}
		
		public String  excute() throws SQLException {
			String value = "";
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			pstm.setString(1,this.name);
			pstm.setString(2,this.cmpnCd);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {			
				value = rs.getString("CONTROL_VALUE");
			}
			
			return value;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM ");
			sql.append("SYSTEM_CONTROL	");
			sql.append("WHERE ");
			sql.append("CONTROL_TX	= ?");
			sql.append("AND CMPN_CD	= ?");
			return sql.toString();
		}
}
