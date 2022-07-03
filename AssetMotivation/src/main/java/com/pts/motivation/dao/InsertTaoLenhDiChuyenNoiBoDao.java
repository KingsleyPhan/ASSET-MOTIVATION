package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class InsertTaoLenhDiChuyenNoiBoDao {
	private LENHDICHUYENNOIBO form;
		public InsertTaoLenhDiChuyenNoiBoDao(LENHDICHUYENNOIBO form) {
			this.form = form;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			pstm.setString(1,form.getId());
			pstm.setString(2,form.getNoNumber());
			pstm.setString(3,form.getSticker());
			pstm.setString(4,form.getStatus());
			pstm.setString(5,form.getCmpnCdOut());
			pstm.setString(6,form.getDeptOut());
			pstm.setString(7,form.getCmpnCdIn());
			pstm.setString(8,form.getDeptIn());
			
			pstm.setString(9,form.getDateStart());
			pstm.setString(10,form.getDateEndScheduale());
			pstm.setString(11,form.getReason());
			pstm.setString(12,form.getDeleteFg());
			pstm.setString(13,form.getInsertDt());
			pstm.setString(14,form.getInsertUser());
			pstm.setString(15,form.getCmpnCd());
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO DICHUYENNOIBO ( ");
			sql.append("		COUPON_CD ");
			sql.append("		,NUMBER_NO ");
			sql.append("		,STICKER ");
			sql.append("		,STATUS ");
			sql.append("		,CMPN_CD_OUT ");
			sql.append("		,DEPT_CD_OUT ");
			sql.append("		,CMPN_CD_IN ");
			sql.append("		,DEPT_CD_IN ");
			sql.append("		,DATE_START ");
			sql.append("		,DATE_END_SCHEDULE ");
			sql.append("		,REASON ");
			sql.append("		,DELETE_FG ");
			sql.append("		,INSERT_DT ");
			sql.append("		,INSERT_USER ");
			sql.append("		,CMPN_CD ");
			sql.append(") VALUES( ");
			sql.append("		? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append(") ");
			return sql.toString();
		}
}
