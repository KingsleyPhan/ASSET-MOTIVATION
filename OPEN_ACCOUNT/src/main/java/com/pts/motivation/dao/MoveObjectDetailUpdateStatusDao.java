package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.AssetMoveObjectDetail;
import com.pts.motivation.model.DEPARTMENT_S;

import com.pts.motivation.model.MoveObject;

public class MoveObjectDetailUpdateStatusDao {
	private AssetMoveObjectDetail assetMove;
		public MoveObjectDetailUpdateStatusDao(AssetMoveObjectDetail assetMove) {
			this.assetMove = assetMove;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			
			pstm.setString(1, assetMove.getStatus2());
			pstm.setString(2, assetMove.getStatus());
			pstm.setString(3, assetMove.getId());
			
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE   MOVE_OBJECT_DETAIL SET  ");
			
			sql.append("		STATUS_2 = ?,  ");
			sql.append("		STATUS = ?  ");
			sql.append("WHERE  ");
			sql.append("		ID = ?  ");
			return sql.toString();
		}
}
