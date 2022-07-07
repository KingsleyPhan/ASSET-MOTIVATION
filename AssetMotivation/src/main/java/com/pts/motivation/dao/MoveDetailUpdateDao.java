package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;

import com.pts.motivation.model.AssetMoveObjectDetail;
import com.pts.motivation.model.DEPARTMENT_S;


public class MoveDetailUpdateDao {
	private AssetMoveObjectDetail asset; 
	private String couponCd;
		public MoveDetailUpdateDao(AssetMoveObjectDetail asset, String couponcd) {
			this.asset  = asset ;
			this.couponCd  = couponcd;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			pstm.setString(1,this.couponCd);
			pstm.setString(2,this.asset.getRfid());
			pstm.setString(3,this.asset.getId());
			pstm.setString(4,this.asset.getName());
			pstm.setString(5,this.asset.getModel());
			pstm.setString(6,this.asset.getSeries());
			pstm.setString(7,this.asset.getAssesseries());
			pstm.setString(8,this.asset.getAccountCd());
			pstm.setString(9,this.asset.getIdMove());
			
			pstm.executeUpdate();
			return true;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE MOVE_OBJECT_DETAIL  SET  ");
			sql.append("		COUPON_CD = ?  ");
			sql.append("		,ASSET_RFID = ?  ");
			sql.append("		,ASSET_CD = ?  ");
			sql.append("		,ASSET_NAME = ?  ");
			sql.append("		,ASSET_MODEL = ?  ");
			sql.append("		,ASSET_SERIES = ?  ");
			sql.append("		,ASSESSERIES = ?  ");
			sql.append("		,ACCOUNTCD = ?  ");
			
			sql.append("WHERE  ");
			sql.append(" ID = ?  ");
			
			System.out.print(sql.toString());
			return sql.toString();
		}
}
