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

import com.pts.motivation.model.DEPARTMENT_S;

import com.pts.motivation.model.MoveObject;
import com.pts.motivation.model.MoveObjectAndAsset;
import com.pts.motivation.model.AssetMoveObjectDetail;

public class MoveObjectAndAssetSelectDao {
		private MoveObject moveObject = null; 
		private AssetMoveObjectDetail asset = null;
	
		
		public MoveObjectAndAssetSelectDao(MoveObject moveObject, AssetMoveObjectDetail asset) {
			this.moveObject = moveObject;
			this.asset = asset;
		}
		
		
		
		public List<MoveObjectAndAsset> excute() throws SQLException {
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			ResultSet rs = pstm.executeQuery();
			
			List<MoveObjectAndAsset> lstResult = new ArrayList<>();
			while(rs.next()) {
				
				MoveObjectAndAsset itemResult = new MoveObjectAndAsset();
				
				AssetMoveObjectDetail item = new AssetMoveObjectDetail();
				MoveObject item2 = new MoveObject();
				
								
				item.setIdMove(rs.getString("ID"));
				item.setId(rs.getString("ASSET_CD"));
				item.setName(rs.getString("ASSET_NAME"));
				item.setRfid(rs.getString("ASSET_RFID"));
				item.setSeries(rs.getString("ASSET_SERIES"));
				item.setModel(rs.getString("ASSET_MODEL"));
				item.setStatusMove(rs.getString("STATUS"));
				item.setAssesseries(rs.getString("ASSESSERIES"));
				item.setStatus2(rs.getString("STATUS_2"));
				
				itemResult.setAsset(item);
				itemResult.setMoveObject(item2);
				
				lstResult.add(itemResult);
			}
			
			return lstResult;
		}
		
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT  *");
			
			
			
			sql.append(" FROM MOVE_OBJECT MO INNER JOIN MOVE_OBJECT_DETAIL  MODTB ON MO.ID = MODTB.COUPON_CD ");
			sql.append(" WHERE 1=1 ");
			
			
			if(asset != null) {
				
				if(UtilCommon.isNotEmpty(this.asset.getId())) {
					sql.append(" AND MODTB.ID = ").append("'"+this.asset.getId()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.asset.getIdMove())) {
					sql.append(" AND MODTB.COUPON_CD = ").append("'"+this.asset.getIdMove()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.asset.getModel())) {
					sql.append(" AND MODTB.ASSET_MODEL = ").append("'"+this.asset.getModel()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.asset.getSeries())) {
					sql.append(" AND MODTB.ASSET_SERIES = ").append("'"+this.asset.getSeries()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.asset.getName())) {
					sql.append(" AND MODTB.ASSET_NAME = ").append("'"+this.asset.getName()+ "'");
				}
				if(UtilCommon.isNotEmpty(this.asset.getRfid())) {
					sql.append(" AND MODTB.ASSET_RFID = ").append("'"+this.asset.getRfid()+ "'");
				}
			}
			
			if(moveObject != null) {
				if(UtilCommon.isEmpty(moveObject.getId()) == false) {
					sql.append(" AND  MO.ID =  ").append("'"+moveObject.getId()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getCmpnCd()) == false) {
					sql.append(" AND  MO.CMPN_CD =  ").append("'"+moveObject.getCmpnCd()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getCode()) == false) {
					sql.append(" AND  MO.CODE =  ").append("'"+moveObject.getCode()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getNo()) == false) {
					sql.append(" AND  MO.NO =  ").append("'"+moveObject.getNo()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getStatus()) == false) {
					sql.append(" AND  MO.STATUS =  ").append("'"+moveObject.getStatus()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getCmpnInId()) == false) {
					sql.append(" AND  MO.CMPN_IN_ID =  ").append("'"+moveObject.getCmpnInId()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getDeptInId()) == false) {
					sql.append(" AND  MO.DEPT_IN_ID =  ").append("'"+moveObject.getDeptInId()+"'");
				}
				
				
				if(UtilCommon.isEmpty(moveObject.getCmpnOutId()) == false) {
					sql.append(" AND  MO.CMPN_OUT_ID =  ").append("'"+moveObject.getCmpnOutId()+"'");
				}
				
				if(UtilCommon.isEmpty(moveObject.getDeptOutId()) == false) {
					sql.append(" AND  MO.DEPT_OUT_ID =  ").append("'"+moveObject.getDeptOutId()+"'");
				}
			}
			return sql.toString();
		}
}
