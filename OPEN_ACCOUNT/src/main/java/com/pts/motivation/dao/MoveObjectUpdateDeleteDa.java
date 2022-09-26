package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;

import com.pts.motivation.model.MoveObject;

public class MoveObjectUpdateDeleteDa {
	private MoveObject moveObject;
		public MoveObjectUpdateDeleteDa(MoveObject moveObject) {
			this.moveObject = moveObject;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			pstm.setString(1, "1");			
			pstm.setString(2, moveObject.getId());
		
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE   MOVE_OBJECT SET  ");
			
			sql.append("		DELETE_FG = ?  ");
		
			sql.append("WHERE  ");
			sql.append("		ID = ?  ");
			return sql.toString();
		}
}
