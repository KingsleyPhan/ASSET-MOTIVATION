package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class UpdateDisApproveLenhDiChuyenNoiBoDao {
	private LENHDICHUYENNOIBO form;
		public UpdateDisApproveLenhDiChuyenNoiBoDao(LENHDICHUYENNOIBO form) {
			this.form = form;
		}
		
		public boolean excuteDisPCD() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLDisPCD()); 
			
			pstm.setString(1,form.getCommentPCD());
			pstm.setString(2,"NOT_PCD");
			pstm.setString(3,form.getApprovePCDUser());
			pstm.setString(4,form.getApprovePCDDt());
			pstm.setString(5,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		private String getSQLDisPCD() {
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
		//-----------------------------------------------------
		public boolean excuteDisKT() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLDisKT()); 
			
			pstm.setString(1,form.getCommentAccount());
			pstm.setString(2,"NOT_KT");
			pstm.setString(3,form.getApproveAccountUser());
			pstm.setString(4,form.getApproveAccountDt());
			pstm.setString(5,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		
		
		private String getSQLDisKT() {
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
		//------------------------------------------------------
		public boolean excuteDisKV() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLDisKV()); 
			
			pstm.setString(1,form.getCommentStock());
			pstm.setString(2,"NOT_KV");
			pstm.setString(3,form.getApproveStockUser());
			pstm.setString(4,form.getApproveStockDt());
			pstm.setString(5,form.getId());
			pstm.executeUpdate();
			return true;
		}
		
		
		
		private String getSQLDisKV() {
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
