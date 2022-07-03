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

public class MoveObjectCountDao {
		String cmpnCd; 
		public MoveObjectCountDao(String cmpnCd) {
			this.cmpnCd = cmpnCd;
		}
		
		public String excute() throws SQLException {
			String value  = null;
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			pstm.setString(1,this.cmpnCd);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				
				value = rs.getString("SL");
			
			}
			
			return value;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) AS SL ");
			sql.append("FROM ");
			sql.append("MOVE_OBJECT	");
			sql.append("WHERE ");
			sql.append("CMPN_CD	= ? ");
			sql.append("AND DELETE_FG = '0'  ");
			System.out.println(sql.toString());
			return sql.toString();
		}
}
