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
import com.pts.motivation.model.ASSET;
import com.pts.motivation.model.DEPARTMENT_S;


public class SelectTaiSanDao {
		
		
		private ASSET asset;
		
		public SelectTaiSanDao(ASSET asset) {
			this.asset = asset;
		}
		
		
		
		public List<ASSET> excute() throws SQLException {
			List<ASSET> lst = new ArrayList<ASSET>();
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				ASSET item = new ASSET();
				item.setId(rs.getString("ASSET_CD"));		
				item.setRfid(rs.getString("ASSET_RFID"));
				item.setModel(rs.getString("ASSET_MODEL"));
				item.setName(rs.getString("ASSET_NAME"));
				item.setSeries(rs.getString("ASSET_SERIES"));
				item.setAccountCd(rs.getString("ASSET_ACCOUNTANT"));
				
				
				lst.add(item);
			}
			
			return lst;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT  ");
			sql.append(" 	ASSET_CD, ");
			sql.append(" 	CMPN_CD, ");
			sql.append(" 	GROUP_CD, ");
			sql.append(" 	ASSET_RFID, ");
			sql.append(" 	ASSET_NAME, ");
			sql.append(" 	ASSET_MODEL, ");
			sql.append(" 	ASSET_SERIES, ");
			sql.append(" 	ASSET_ACCOUNTANT ");
			sql.append(" FROM ASSETS_GENERAL ");
			sql.append(" 	WHERE DELETE_FG = '0'  ");
			if(asset != null) {
				if(UtilCommon.isNotEmpty(asset.getDeptCd())) {
					//sql.append(" AND ASSET_DEPARTMENT =  ").append("'" + asset.getDeptCd()+"'");
				}
				if(UtilCommon.isNotEmpty(asset.getRfid())) {
					sql.append(" AND ASSET_RFID =  ").append("'" + asset.getRfid()+"'");
				}
				if(UtilCommon.isNotEmpty(asset.getModel())) {
					sql.append(" AND ASSET_MODEL =  ").append("'" + asset.getModel()+"'");
				}
				if(UtilCommon.isNotEmpty(asset.getSeries())) {
					sql.append(" AND ASSET_SERIES =  ").append("'" + asset.getSeries()+"'");
				}
				if(UtilCommon.isNotEmpty(asset.getName())) {
					sql.append(" AND ASSET_NAME =  ").append("'" + asset.getName()+"'");
				}
			}
			
		
			
			System.out.print(sql.toString());
			return sql.toString();
		}
}
