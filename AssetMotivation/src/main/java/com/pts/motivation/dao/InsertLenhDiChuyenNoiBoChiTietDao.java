package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class InsertLenhDiChuyenNoiBoChiTietDao {
	private ASSETDICHUYENNOIBO asset; 
	private String couponCd;
		public InsertLenhDiChuyenNoiBoChiTietDao(ASSETDICHUYENNOIBO asset, String couponcd) {
			this.asset  = asset ;
			this.couponCd  = couponcd;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			
			pstm.setString(1,this.asset.getIdMove());
			pstm.setString(2,this.couponCd);
			pstm.setString(3,this.asset.getId());
			pstm.setString(4,this.asset.getRfid());
			
			pstm.setString(5,this.asset.getName());
			pstm.setString(6,this.asset.getSeries());
			pstm.setString(7,this.asset.getModel());
			pstm.setString(8,"NEW");
			pstm.setString(9,this.asset.getAssesseries());
			

			
			pstm.executeUpdate();
			return true;
		}
		
		

		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO DICHUYENNOIBO_DETAIL ( ");
			sql.append("		ID ");
			sql.append("		,COUPON_CD ");
			sql.append("		,ASSET_CD ");
			sql.append("		,ASSET_RFID ");
			sql.append("		,ASSET_NAME ");
			sql.append("		,ASSET_SERIES ");
			sql.append("		,ASSET_MODEL ");
			sql.append("		,STATUS ");
			sql.append("		,ASSESSERIES ");
			sql.append(") VALUES( ");
			sql.append("		? ");
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
