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
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.COMPANY;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;
import com.pts.motivation.model.MoveObject;
import com.pts.motivation.model.AssetMoveObjectDetail;

public class MoveObjectDetailSelectDao {
		private MoveObject lenh = null; 
	
		
		public MoveObjectDetailSelectDao(MoveObject lenh) {
			this.lenh = lenh;
		}
		
		
		
		public MoveObject excute() throws SQLException {
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			ResultSet rs = pstm.executeQuery();
			
			MoveObject LENH = new MoveObject();
			while(rs.next()) {
				
				AssetMoveObjectDetail item = new AssetMoveObjectDetail();
								
				item.setIdMove(rs.getString("ID"));
				item.setId(rs.getString("ASSET_CD"));
				item.setName(rs.getString("ASSET_NAME"));
				item.setRfid(rs.getString("ASSET_RFID"));
				item.setSeries(rs.getString("ASSET_SERIES"));
				item.setModel(rs.getString("ASSET_MODEL"));
				item.setStatusMove(rs.getString("STATUS"));
				item.setAssesseries(rs.getString("ASSESSERIES"));
				
				
				LENH.getLstAsset().add(item);
			}
			
			return LENH;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append("    ID , ");
			sql.append("    COUPON_CD , ");
			sql.append("    ASSET_CD , ");
			sql.append("    ASSET_RFID , ");
			sql.append("    ASSET_NAME, ");
			sql.append("    ASSET_MODEL , ");
			sql.append("    ASSET_SERIES, ");
			sql.append("    STATUS, ");
			sql.append("    ASSESSERIES ");
			
			
			sql.append(" FROM MOVE_OBJECT_DETAIL DCCT ");
			sql.append(" WHERE 1=1 ");
			
			
			if(lenh != null) {
				
				if(UtilCommon.isNotEmpty(this.lenh.getId())) {
					sql.append(" AND DCCT.COUPON_CD = ").append("'"+this.lenh.getId()+ "'");
				}
			}
			return sql.toString();
		}
}
