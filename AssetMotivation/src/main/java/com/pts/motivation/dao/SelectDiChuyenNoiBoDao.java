package com.pts.motivation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.model.COMPANY;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class SelectDiChuyenNoiBoDao {
		private String cmpnCd = null; 
		private LENHDICHUYENNOIBO lenh = null; 
		
		
		public SelectDiChuyenNoiBoDao(String cmpnCd) {
			this.cmpnCd = cmpnCd;
		}
		
		public SelectDiChuyenNoiBoDao(LENHDICHUYENNOIBO lenh) {
			this.lenh = lenh;
		}
		
		
		
		public List<LENHDICHUYENNOIBO> excute() throws SQLException {
			List<LENHDICHUYENNOIBO> lst = new ArrayList<LENHDICHUYENNOIBO>();
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				LENHDICHUYENNOIBO item = new LENHDICHUYENNOIBO();
				item.setId(rs.getString("COUPON_CD"));
				item.setNoNumber(rs.getString("NUMBER_NO"));
				item.setSticker(rs.getString("STICKER"));
				item.setStatus(rs.getString("STATUS").trim());
				
				item.setCmpnCdOut(rs.getString("CMPN_CD_OUT"));
				item.setCmpnOutName(rs.getString("CMPN_OUT_NAME"));
				item.setDeptOut(rs.getString("DEPT_CD_OUT"));
				item.setDeptOutName(rs.getString("DEPT_OUT_NAME"));
				
				item.setCmpnCdIn(rs.getString("CMPN_CD_IN"));
				item.setCmpnInName(rs.getString("CMPN_IN_NAME"));				
				item.setDeptIn(rs.getString("DEPT_CD_IN"));
				item.setDeptInName(rs.getString("DEPT_IN_NAME"));
				
				item.setDateStart(rs.getString("DATE_START"));
				item.setDateEndScheduale(rs.getString("DATE_END_SCHEDULE"));
				item.setDateEndReal(rs.getString("DATE_END_REAL"));
				
				item.setReason(rs.getString("REASON"));
				
				item.setInsertUser(rs.getString("INSERT_USER"));
				item.setInsertDt(rs.getString("INSERT_DT"));
				item.setCommentCreate(rs.getString("COMMENT_CREATE"));
				
				item.setApprovePCDUser(rs.getString("DEPT_APPROVE_USER"));
				item.setApprovePCDDt(rs.getString("DEPT_APPROVE_DT"));
				item.setCommentPCD(rs.getString("COMMENT_PCD"));
				
				item.setApproveAccountUser(rs.getString("ACCOUNT_APROVE_USER"));
				item.setApproveAccountDt(rs.getString("ACCOUNT_APPROVE_DT"));
				item.setCommentAccount(rs.getString("COMMENT_KT"));
				
				item.setApproveStockUser(rs.getString("STOCK_APPROVE_USER"));
				item.setApproveStockDt(rs.getString("STOCK_APPROVE_DT"));
				item.setCommentStock(rs.getString("COMMENT_KV"));
				
				
				
				lst.add(item);
			}
			
			return lst;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append("    COUPON_CD , ");
			sql.append("    NUMBER_NO , ");
			sql.append("    STICKER , ");
			sql.append("    STATUS, ");

			sql.append("    CMPN_CD_OUT, ");
			sql.append("    CM1.CMPN_NAME AS CMPN_OUT_NAME, ");
			sql.append("    CMPN_CD_IN, ");
			sql.append("    CM2.CMPN_NAME AS CMPN_IN_NAME, ");

			sql.append("    DEPT_CD_OUT, ");
			sql.append("    DP1.DEPARTMENT_NAME AS DEPT_OUT_NAME, ");
			sql.append("    DEPT_CD_IN, ");
			sql.append("    DP2.DEPARTMENT_NAME AS DEPT_IN_NAME, ");

			sql.append("    DATE_START, ");
			sql.append("    DATE_END_SCHEDULE, ");
			sql.append("    DATE_END_REAL, ");

			sql.append("    REASON, ");

			sql.append("    INSERT_USER, ");
			sql.append("    DC.INSERT_DT, ");
			sql.append("    COMMENT_CREATE, ");

			sql.append("    DEPT_APPROVE_USER, ");
			sql.append("     DEPT_APPROVE_DT, ");
			sql.append("    COMMENT_PCD, ");

			sql.append("    ACCOUNT_APROVE_USER, ");
			sql.append("    ACCOUNT_APPROVE_DT, ");
			sql.append("     COMMENT_KT, ");

			sql.append("     STOCK_APPROVE_USER, ");
			sql.append("    STOCK_APPROVE_DT, ");
			sql.append("    COMMENT_KV ");

			sql.append(" FROM DICHUYENNOIBO DC ");
			sql.append(" LEFT JOIN COMPANY CM1  ");
			sql.append(" 		ON DC.CMPN_CD_OUT = CM1.CMPN_CD ");
			sql.append(" LEFT JOIN COMPANY CM2  ");
			sql.append(" 		ON DC.CMPN_CD_IN = CM2.CMPN_CD ");
			sql.append(" LEFT JOIN DEPRATMENT DP1  ");
			sql.append(" 		ON DC.DEPT_CD_OUT = DP1.DEPT_CD ");
			sql.append(" LEFT JOIN DEPRATMENT DP2  ");
			sql.append(" 			ON DC.DEPT_CD_IN = DP2.DEPT_CD ");
			sql.append(" WHERE DC.DELETE_FG = '0' ");
			if(UtilCommon.isNotEmpty(this.cmpnCd)) {
				sql.append(" AND DC.CMPN_CD = ").append("'"+this.cmpnCd + "'");
			}
			
			if(lenh != null) {
				if(UtilCommon.isNotEmpty(this.lenh.getId())) {
					sql.append(" AND DC.COUPON_CD = ").append("'"+this.lenh.getId()+ "'");
				}
				
				if(UtilCommon.isNotEmpty(lenh.getCmpnCdIn())) {
					sql.append(" AND DC.CMPN_CD_IN = ").append("'"+this.lenh.getCmpnCdIn()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getCmpnCdOut())) {
					sql.append(" AND DC.CMPN_CD_OUT = ").append("'"+this.lenh.getCmpnCdOut()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getDeptIn())) {
					sql.append(" AND DC.DEPT_CD_IN = ").append("'"+this.lenh.getDeptIn()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getDeptOut())) {
					sql.append(" AND DC.DEPT_CD_OUT = ").append("'"+this.lenh.getDeptOut()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getDateStart())) {
					sql.append(" AND DC.DATE_START = ").append("'"+this.lenh.getDateStart()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getNoNumber())) {
					sql.append(" AND DC.NUMBER_NO = ").append("'"+this.lenh.getNoNumber()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getStatus())) {
					sql.append(" AND DC.STATUS = ").append("'"+this.lenh.getStatus()+ "'");
				}
				if(UtilCommon.isNotEmpty(lenh.getCmpnCd())) {
					sql.append(" AND DC.CMPN_CD = ").append("'"+this.lenh.getCmpnCd()+ "'");
				}
			}
			System.out.print(sql.toString());
			return sql.toString();
		}
}
