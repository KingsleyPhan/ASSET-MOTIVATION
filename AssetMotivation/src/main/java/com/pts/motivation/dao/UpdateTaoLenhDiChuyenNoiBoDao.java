package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class UpdateTaoLenhDiChuyenNoiBoDao {
	private LENHDICHUYENNOIBO form;
		public UpdateTaoLenhDiChuyenNoiBoDao(LENHDICHUYENNOIBO form) {
			this.form = form;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			pstm.setString(1,form.getNoNumber());
			pstm.setString(2,form.getSticker());
			pstm.setString(3,form.getStatus());
			pstm.setString(4,form.getCmpnCdOut());
			pstm.setString(5,form.getDeptOut());
			pstm.setString(6,form.getCmpnCdIn());
			pstm.setString(7,form.getDeptIn());
			
			pstm.setString(8,form.getDateStart());
			pstm.setString(9,form.getDateEndScheduale());
			pstm.setString(10,form.getReason());
			pstm.setString(11,form.getDeleteFg());
			pstm.setString(12,form.getUpdateDt());
			pstm.setString(13,form.getUpdateUser());
			
			pstm.setString(14,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE   ASSETS_GENERAL SET  ");
			sql.append("		ASSET_DEPARTMENT = ?  ");
			sql.append("		,STICKER = ?  ");
			sql.append("		,STATUS = ?  ");
			sql.append("		,CMPN_CD_OUT = ?  ");
			sql.append("		,DEPT_CD_OUT = ?  ");
			sql.append("		,CMPN_CD_IN = ?  ");
			sql.append("		,DEPT_CD_IN = ?  ");
			sql.append("		,DATE_START = ?  ");
			sql.append("		,DATE_END_SCHEDULE = ?  ");
			sql.append("		,REASON = ?  ");
			sql.append("		,DELETE_FG = ?  ");
			sql.append("		,UPDATE_DT = ?  ");
			sql.append("		,UPDATE_USER = ?  ");
			sql.append("WHERE  ");
			sql.append("		COUPON_CD = ?  ");
			return sql.toString();
		}
}
