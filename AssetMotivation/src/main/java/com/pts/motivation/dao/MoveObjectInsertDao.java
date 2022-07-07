package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;

import com.pts.motivation.model.MoveObject;

public class MoveObjectInsertDao {
	
		private MoveObject moveObject;
		
		public MoveObjectInsertDao(MoveObject moveObject) {
			this.moveObject = moveObject;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			pstm.setString(1, moveObject.getId());
			pstm.setString(2, moveObject.getCode());
			pstm.setString(3, moveObject.getNo());
			
			pstm.setString(4, moveObject.getCmpnOutId());
			pstm.setString(5, moveObject.getCmpnOutName());
			
			pstm.setString(6, moveObject.getDeptOutId());
			pstm.setString(7, moveObject.getDeptOutName());
			pstm.setString(8, moveObject.getDateOut());		
			
			pstm.setString(9, moveObject.getCmpnInId());
			pstm.setString(10, moveObject.getCmpnInName());
			
			pstm.setString(11, moveObject.getDeptInId());
			pstm.setString(12, moveObject.getDeptInName());
			pstm.setString(13, moveObject.getDateIn());
			
			pstm.setString(14, moveObject.getReason());
			
			pstm.setString(15, moveObject.getUserCreate());
			pstm.setString(16, moveObject.getDateCreate());
			pstm.setString(17, moveObject.getNoteCreate());
			
			pstm.setString(18, moveObject.getCommentManager());
			pstm.setString(19, moveObject.getUserManager());
			pstm.setString(20, moveObject.getDateManager());
			pstm.setString(21, moveObject.getNoteManager());
			
			pstm.setString(22, moveObject.getCommentAccount());
			pstm.setString(23, moveObject.getUserAccount());
			pstm.setString(24, moveObject.getDateAccount());
			pstm.setString(25, moveObject.getNoteAccount());
			
			pstm.setString(26, moveObject.getCommentStock());
			pstm.setString(27, moveObject.getUserStock());
			pstm.setString(28, moveObject.getDateStock());
			pstm.setString(29, moveObject.getNoteStock());
			
			pstm.setString(30, moveObject.getStatus());
			pstm.setString(31, moveObject.getDeleteFg());
			pstm.setString(32, moveObject.getCmpnCd());

	
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MOVE_OBJECT ( ");
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
			sql.append(") VALUES ( ");
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
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append(") ");
			return sql.toString();
		}
}
