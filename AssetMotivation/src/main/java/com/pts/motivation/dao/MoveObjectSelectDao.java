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
import com.pts.motivation.model.MoveObject;
import com.pts.motivation.model.USER;

public class MoveObjectSelectDao {
	private MoveObject moveObject; 
	
	public MoveObjectSelectDao(MoveObject moveObject) {
		this.moveObject = moveObject;
	}
	
	public List<MoveObject> excute() throws SQLException {
		List<MoveObject> lstMove = new ArrayList<MoveObject>();
		
		DatabaseConnection conn = new  DatabaseConnection();
		Connection connEx = conn.getConnection();
		Statement stm = connEx.createStatement();
	
		ResultSet rs = stm.executeQuery(getSQL());
		
		while(rs.next()) {
			MoveObject item = new MoveObject();
			item.setId(rs.getString("ID"));
			item.setNo(rs.getString("NO"));
			item.setCode(rs.getString("CODE"));
			item.setCmpnOutId(rs.getString("CMPN_OUT_ID"));
			item.setCmpnOutName(rs.getString("CMPN_OUT_NAME"));
			item.setDeptOutId(rs.getString("DEPT_OUT_ID"));
			item.setDeptOutName(rs.getString("DEPT_OUT_NAME"));
			item.setDateOut(rs.getString("DATE_OUT"));
			item.setCmpnInId(rs.getString("CMPN_IN_ID"));
			item.setCmpnInName(rs.getString("CMPN_IN_NAME"));
			item.setDeptInId(rs.getString("DEPT_IN_ID"));
			item.setDeptInName(rs.getString("DEPT_IN_NAME"));
			item.setDateIn(rs.getString("DATE_IN"));
			item.setReason(rs.getString("REASON"));
			item.setUserCreate(rs.getString("USER_CREATE"));
			item.setDateCreate(rs.getString("DATE_CREATE"));
			item.setNoteCreate(rs.getString("NOTE_CREATE"));
			item.setCommentManager(rs.getString("COMMENT_MANAGER"));
			item.setUserManager(rs.getString("USER_MANAGER"));
			item.setDateManager(rs.getString("DATE_MANAGER"));
			item.setNoteManager(rs.getString("NOTE_MANAGER"));
			item.setCommentAccount(rs.getString("COMMENT_ACCOUNT"));
			item.setUserAccount(rs.getString("USER_ACCOUNT"));
			item.setDateAccount(rs.getString("DATE_ACCOUNT"));
			item.setNoteAccount(rs.getString("NOTE_ACCOUNT"));
			item.setCommentStock(rs.getString("COMMENT_STOCK"));
			item.setUserStock(rs.getString("USER_STOCK"));
			item.setDateStock(rs.getString("DATE_STOCK"));
			item.setNoteStock(rs.getString("NOTE_STOCK"));
			item.setStatus(rs.getString("STATUS"));
			item.setDeleteFg(rs.getString("DELETE_FG"));
			item.setCmpnCd(rs.getString("CMPN_CD"));
			
			
			
			lstMove.add(item);
		}
		return lstMove;
	}
	
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("		ID, ");
		sql.append("		CODE, ");
		sql.append("		NO, ");
		sql.append("		CMPN_OUT_ID, ");
		sql.append("		CMPN_OUT_NAME, ");
		sql.append("		DEPT_OUT_ID, ");
		sql.append("		DEPT_OUT_NAME, ");
		sql.append("		DATE_OUT, ");
		sql.append("		CMPN_IN_ID, ");
		sql.append("		CMPN_IN_NAME, ");
		sql.append("		DEPT_IN_ID, ");
		sql.append("		DEPT_IN_NAME, ");
		sql.append("		DATE_IN, ");
		sql.append("		REASON, ");
		sql.append("		USER_CREATE, ");
		sql.append("		DATE_CREATE, ");
		sql.append("		NOTE_CREATE, ");
		sql.append("		COMMENT_MANAGER, ");
		sql.append("		USER_MANAGER, ");
		sql.append("		DATE_MANAGER, ");
		sql.append("		NOTE_MANAGER, ");
		sql.append("		COMMENT_ACCOUNT, ");
		sql.append("		USER_ACCOUNT, ");
		sql.append("		DATE_ACCOUNT, ");
		sql.append("		NOTE_ACCOUNT, ");
		sql.append("		COMMENT_STOCK, ");
		sql.append("		USER_STOCK, ");
		sql.append("		DATE_STOCK, ");
		sql.append("		NOTE_STOCK, ");
		sql.append("		STATUS, ");
		sql.append("		DELETE_FG, ");
		sql.append("		CMPN_CD ");
		sql.append(" FROM MOVE_OBJECT ");
		sql.append(" WHERE  DELETE_FG = '0' ");
		if(moveObject != null) {
			if(UtilCommon.isEmpty(moveObject.getId()) == false) {
				sql.append(" AND  ID =  ").append("'"+moveObject.getId()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getCmpnCd()) == false) {
				sql.append(" AND  CMPN_CD =  ").append("'"+moveObject.getCmpnCd()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getCode()) == false) {
				sql.append(" AND  CODE =  ").append("'"+moveObject.getCode()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getNo()) == false) {
				sql.append(" AND  NO =  ").append("'"+moveObject.getNo()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getStatus()) == false) {
				sql.append(" AND  STATUS =  ").append("'"+moveObject.getStatus()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getCmpnInId()) == false) {
				sql.append(" AND  CMPN_IN_ID =  ").append("'"+moveObject.getCmpnInId()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getDeptInId()) == false) {
				sql.append(" AND  DEPT_IN_ID =  ").append("'"+moveObject.getDeptInId()+"'");
			}
			
			
			if(UtilCommon.isEmpty(moveObject.getCmpnOutId()) == false) {
				sql.append(" AND  CMPN_OUT_ID =  ").append("'"+moveObject.getCmpnOutId()+"'");
			}
			
			if(UtilCommon.isEmpty(moveObject.getDeptOutId()) == false) {
				sql.append(" AND  DEPT_OUT_ID =  ").append("'"+moveObject.getDeptOutId()+"'");
			}
			if(UtilCommon.isNotEmpty(this.moveObject.getDateStart())) {
				sql.append(" AND convert(nvarchar, CONVERT(DATETIME, DATE_CREATE, 103), 112) >= ").append("'"+this.moveObject.getDateStart().trim()+ "'");
			}
			
			if(UtilCommon.isNotEmpty(this.moveObject.getDateEnd())) {
				sql.append(" AND convert(nvarchar, CONVERT(DATETIME, DATE_CREATE, 103), 112) <= ").append("'"+this.moveObject.getDateEnd().trim()+ "'");
			}
		}
		
		sql.append(" ORDER BY CAST(NO AS int)   ");
		System.out.println(sql.toString());
		return sql.toString();
	}
}
