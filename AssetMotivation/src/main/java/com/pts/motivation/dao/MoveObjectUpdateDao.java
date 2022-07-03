package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;
import com.pts.motivation.model.MoveObject;

public class MoveObjectUpdateDao {
	private MoveObject moveObject;
		public MoveObjectUpdateDao(MoveObject moveObject) {
			this.moveObject = moveObject;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			
			pstm.setString(1, moveObject.getCode());
			pstm.setString(2, moveObject.getNo());
			
			pstm.setString(3, moveObject.getCmpnOutId());
			pstm.setString(4, moveObject.getCmpnOutName());
			
			pstm.setString(5, moveObject.getDeptOutId());
			pstm.setString(6, moveObject.getDeptOutName());
			pstm.setString(7, moveObject.getDateOut());		
			
			pstm.setString(8, moveObject.getCmpnInId());
			pstm.setString(9, moveObject.getCmpnInName());
			
			pstm.setString(10, moveObject.getDeptInId());
			pstm.setString(11, moveObject.getDeptInName());
			pstm.setString(12, moveObject.getDateIn());
			
			pstm.setString(13, moveObject.getReason());
			
			pstm.setString(14, moveObject.getUserCreate());
			pstm.setString(15, moveObject.getDateCreate());
			pstm.setString(16, moveObject.getNoteCreate());
			
			pstm.setString(17, moveObject.getCommentManager());
			pstm.setString(18, moveObject.getUserManager());
			pstm.setString(19, moveObject.getDateManager());
			pstm.setString(20, moveObject.getNoteManager());
			
			pstm.setString(21, moveObject.getCommentAccount());
			pstm.setString(22, moveObject.getUserAccount());
			pstm.setString(23, moveObject.getDateAccount());
			pstm.setString(24, moveObject.getNoteAccount());
			
			pstm.setString(25, moveObject.getCommentStock());
			pstm.setString(26, moveObject.getUserStock());
			pstm.setString(27, moveObject.getDateStock());
			pstm.setString(28, moveObject.getNoteStock());
			
			pstm.setString(29, moveObject.getStatus());
			pstm.setString(30, moveObject.getDeleteFg());
			pstm.setString(31, moveObject.getCmpnCd());
			
			pstm.setString(32, moveObject.getId());

			
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE   MOVE_OBJECT SET  ");
			sql.append("		CODE = ?,  ");
			sql.append("		NO = ?,  ");
			sql.append("		CMPN_OUT_ID = ?,  ");
			sql.append("		CMPN_OUT_NAME = ?,  ");
			sql.append("		DEPT_OUT_ID = ?,  ");
			sql.append("		DEPT_OUT_NAME = ?,  ");
			sql.append("		DATE_OUT = ?,  ");
			sql.append("		CMPN_IN_ID = ?,  ");
			sql.append("		CMPN_IN_NAME = ?,  ");
			sql.append("		DEPT_IN_ID = ?,  ");
			sql.append("		DEPT_IN_NAME = ?,  ");
			sql.append("		DATE_IN = ?,  ");
			sql.append("		REASON = ?,  ");
			sql.append("		USER_CREATE = ?,  ");
			sql.append("		DATE_CREATE = ?,  ");
			sql.append("		NOTE_CREATE = ?,  ");
			sql.append("		COMMENT_MANAGER = ?,  ");
			sql.append("		USER_MANAGER = ?,  ");
			sql.append("		DATE_MANAGER = ?,  ");
			sql.append("		NOTE_MANAGER = ?,  ");
			sql.append("		COMMENT_ACCOUNT = ?,  ");
			sql.append("		USER_ACCOUNT = ?,  ");
			sql.append("		DATE_ACCOUNT = ?,  ");
			sql.append("		NOTE_ACCOUNT = ?,  ");
			sql.append("		COMMENT_STOCK = ?,  ");
			sql.append("		USER_STOCK = ?,  ");
			sql.append("		DATE_STOCK = ?,  ");
			sql.append("		NOTE_STOCK = ?,  ");
			sql.append("		STATUS = ?,  ");
			sql.append("		DELETE_FG = ?,  ");
			sql.append("		CMPN_CD = ?  ");
			sql.append("WHERE  ");
			sql.append("		ID = ?  ");
			return sql.toString();
		}
}
