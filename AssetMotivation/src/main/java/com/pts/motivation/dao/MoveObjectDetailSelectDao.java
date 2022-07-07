package com.pts.motivation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.common.UtilCommon;

import com.pts.motivation.model.DEPARTMENT_S;

import com.pts.motivation.model.MoveObject;
import com.pts.motivation.model.AssetMoveObjectDetail;

public class MoveObjectDetailSelectDao {
		private MoveObject lenh = null; 
	
		
		public MoveObjectDetailSelectDao(MoveObject lenh) {
			this.lenh = lenh;
		}
		
		
		
		public MoveObject excute() throws SQLException {
			DatabaseConnection conn = new DatabaseConnection();
			System.out.println(getSQL());
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
				item.setStatus2(rs.getString("STATUS_2"));
				item.setStatus(rs.getString("STATUS"));
				item.setAccountCd(rs.getString("ACCOUNTCD"));
				item.setPrice(priceWithDecimal(rs.getString("ASSET_PRICE")));
				
				if(item.getName().trim().length() > 0 && item.getSeries().trim().length()>0 && item.getModel().trim().length() > 0) {
					LENH.getLstAsset().add(item);
				}
				
			}
			
			return LENH;
		}
		
		public String priceWithDecimal (String price) {
			try {
				Double xxx = Double.parseDouble(price);
			    DecimalFormat formatter = new DecimalFormat("###,###,###");
			    return formatter.format(xxx);
			} catch (Exception e) {
				// TODO: handle exception
				return "0";
			}
			
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append("    DCCT.ID , ");
			sql.append("    DCCT.COUPON_CD , ");
			sql.append("    DCCT.ASSET_CD , ");
			sql.append("    DCCT.ASSET_RFID , ");
			sql.append("    DCCT.ASSET_NAME, ");
			sql.append("    DCCT.ASSET_MODEL , ");
			sql.append("    DCCT.ASSET_SERIES, ");
			sql.append("    DCCT.STATUS, ");
			sql.append("    DCCT.ASSESSERIES, ");
			sql.append("    DCCT.STATUS_2, ");
			sql.append("    DCCT.ACCOUNTCD, ");
			sql.append("    ISNULL(AG.ASSET_PRICE,0) as  ASSET_PRICE");
			
			
			sql.append(" FROM MOVE_OBJECT_DETAIL DCCT ");
			sql.append(" LEFT JOIN ASSETS_GENERAL AG ON AG.ASSET_RFID =DCCT.ASSET_RFID  ");
			sql.append(" WHERE 1=1 ");
			
			
			if(lenh != null) {
				
				if(UtilCommon.isNotEmpty(this.lenh.getId())) {
					sql.append(" AND DCCT.COUPON_CD = ").append("'"+this.lenh.getId()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.lenh.getNameAsset())) {
					sql.append(" AND DCCT.ASSET_NAME = ").append("'"+this.lenh.getId().trim()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.lenh.getModelAsset())) {
					sql.append(" AND DCCT.ASSET_MODEL = ").append("'"+this.lenh.getModelAsset().trim()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.lenh.getSeriesAsset())) {
					sql.append(" AND DCCT.ASSET_SERIES = ").append("'"+this.lenh.getSeriesAsset().trim()+ "'");
				}
				
				
			}
			return sql.toString();
		}
}
