package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class UpdateApproveLenhDiChuyenNoiBoDao {
	private LENHDICHUYENNOIBO form;
		public UpdateApproveLenhDiChuyenNoiBoDao(LENHDICHUYENNOIBO form) {
			this.form = form;
		}
		
		public boolean excutePCD() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLPCD()); 
			
			pstm.setString(1,form.getCommentPCD());
			pstm.setString(2,"PCD");
			pstm.setString(3,form.getApprovePCDUser());
			pstm.setString(4,form.getApprovePCDDt());
			pstm.setString(5,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		private String getSQLPCD() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE DICHUYENNOIBO SET  ");
			sql.append("		COMMENT_PCD= ?  ");
			sql.append("		,STATUS= ?  ");
			sql.append("		,DEPT_APPROVE_USER= ?  ");
			sql.append("		,DEPT_APPROVE_DT= ?  ");
			sql.append("WHERE  ");
			sql.append("		COUPON_CD = ?  ");
			return sql.toString();
		}
		//-------------------------------------------------------------------------------------
		public boolean excuteKT() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLKT()); 
			
			pstm.setString(1,form.getCommentAccount());
			pstm.setString(2,"KT");
			pstm.setString(3,form.getApproveAccountUser());
			pstm.setString(4,form.getApproveAccountDt());
			pstm.setString(5,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		
		
		private String getSQLKT() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE DICHUYENNOIBO SET  ");
			sql.append("		COMMENT_KT= ?  ");
			sql.append("		,STATUS= ?  ");
			sql.append("		,ACCOUNT_APROVE_USER= ?  ");
			sql.append("		,ACCOUNT_APPROVE_DT= ?  ");
			sql.append("WHERE  ");
			sql.append("		COUPON_CD = ?  ");
			return sql.toString();
		}
		
		public boolean excuteKV() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLKV()); 
			
			pstm.setString(1,form.getCommentStock());
			pstm.setString(2,"OK");
			pstm.setString(3,form.getApproveStockUser());
			pstm.setString(4,form.getApproveStockDt());
			pstm.setString(5,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		
		
		private String getSQLKV() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE DICHUYENNOIBO SET  ");
			sql.append("		COMMENT_KV= ?  ");
			sql.append("		,STATUS= ?  ");
			sql.append("		,STOCK_APPROVE_USER= ?  ");
			sql.append("		,STOCK_APPROVE_DT= ?  ");
			sql.append("WHERE  ");
			sql.append("		COUPON_CD = ?  ");
			return sql.toString();
		}
}
