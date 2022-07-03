package com.pts.motivation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.AssetMoveObjectDetail;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;

public class UpdateDonViTaiSanLenhDiChuyenNoiBoDao {
	private ASSETDICHUYENNOIBO form1;
	private AssetMoveObjectDetail form2;
	
	public UpdateDonViTaiSanLenhDiChuyenNoiBoDao(ASSETDICHUYENNOIBO form) {
		this.form1 = form;
	}
	
	public UpdateDonViTaiSanLenhDiChuyenNoiBoDao(AssetMoveObjectDetail form) {
		this.form2 = form;
	}
	
	public boolean excute() throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
		
		if(form1 !=null) {
			pstm.setString(1,form1.getDeptCd());
			pstm.setString(2,form1.getRfid());
		} else {
			pstm.setString(1,form2.getDeptCd());
			pstm.setString(2,form2.getRfid());
		}
	
		
		pstm.executeUpdate();
		return true;
	}
	
	public boolean excuteNhapMuon(String cmpnId_Nhap) throws SQLException {
		
		DatabaseConnection conn = new DatabaseConnection();
		PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQLNhapMuon()); 
		
			pstm.setString(1,cmpnId_Nhap);
			pstm.setString(2,form2.getDeptCd());
			pstm.setString(3,form2.getRfid());
		
	
		
		pstm.executeUpdate();
		return true;
	}
	
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE   ASSETS_GENERAL SET  ");
		sql.append("		ASSET_DEPARTMENT = ?  ");
		sql.append("WHERE  ");
		sql.append("		ASSET_RFID = ?  ");
		return sql.toString();
	}
	
	public String getSQLNhapMuon() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE   ASSETS_GENERAL SET  ");
		sql.append("		CMPN_CD = ? , ");
		sql.append("		ASSET_DEPARTMENT = ?  ");
		sql.append("WHERE  ");
		sql.append("		ASSET_RFID = ?  ");
		return sql.toString();
	}
}
