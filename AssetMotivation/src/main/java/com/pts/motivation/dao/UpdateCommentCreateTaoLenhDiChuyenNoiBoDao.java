package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class UpdateCommentCreateTaoLenhDiChuyenNoiBoDao {
	private LENHDICHUYENNOIBO form;
		public UpdateCommentCreateTaoLenhDiChuyenNoiBoDao(LENHDICHUYENNOIBO form) {
			this.form = form;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			pstm.setString(1,form.getCommentCreate());
			pstm.setString(2,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE DICHUYENNOIBO SET  ");
			sql.append("		COMMENT_CREATE= ?  ");
			sql.append("WHERE  ");
			sql.append("		COUPON_CD = ?  ");
			return sql.toString();
		}
}
