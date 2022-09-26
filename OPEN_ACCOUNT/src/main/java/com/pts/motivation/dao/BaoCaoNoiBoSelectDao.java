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
import com.pts.motivation.model.BaoCaoNoiBoDetail;
import com.pts.motivation.model.MoveObject;
import com.pts.motivation.model.USER;

public class BaoCaoNoiBoSelectDao {
	private BaoCaoNoiBoDetail baoCao; 
	
	public BaoCaoNoiBoSelectDao(BaoCaoNoiBoDetail baoCao) {
		this.baoCao = baoCao;
	}
	
	public List<BaoCaoNoiBoDetail> excute() throws SQLException {
		List<BaoCaoNoiBoDetail> lstBaoCao = new ArrayList<BaoCaoNoiBoDetail>();
		
		DatabaseConnection conn = new  DatabaseConnection();
		Connection connEx = conn.getConnection();
		Statement stm = connEx.createStatement();
	
		ResultSet rs = stm.executeQuery(getSQL());
		
		while(rs.next()) {
			BaoCaoNoiBoDetail item = new BaoCaoNoiBoDetail();
			item.setDeptCd(rs.getString("ASSET_DEPARTMENT"));
			item.setDeptName(rs.getString("DEPARTMENT_NAME"));
			item.setGroupCd(rs.getString("GROUP_CD"));
			item.setGroupName(rs.getString("GROUP_NAME"));
			item.setModel(rs.getString("ASSET_MODEL"));
			item.setName(rs.getString("ASSET_NAME"));
			item.setSl(rs.getString("SL"));		
			lstBaoCao.add(item);
		}
		return lstBaoCao;
	}
	
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append(" SELECT ASSET_DEPARTMENT, DEPARTMENT_NAME, GROUP_CD, TBL.GROUP_NAME, ASSET_MODEL, ASSET_NAME, COUNT(ASSET_NAME) AS SL ");
		sql.append(" FROM ( ");
		sql.append(" SELECT  ");
		sql.append(" 		AG.ASSET_CD, ");
		sql.append(" 		AG.CMPN_CD, ");
		sql.append(" 		AG.GROUP_CD, ");
		sql.append(" 		GA.GROUP_NAME, ");
		sql.append(" 		AG.ASSET_NAME, ");
		sql.append(" 		AG.ASSET_MODEL, ");
		sql.append(" 		AG.ASSET_DEPARTMENT, ");
		sql.append(" 		DPT.DEPARTMENT_NAME ");
		sql.append(" FROM ASSETS_GENERAL AG ");
		sql.append(" LEFT JOIN GROUP_ASSET GA ON AG.GROUP_CD = GA.GROUP_ID ");
		sql.append(" LEFT JOIN DEPRATMENT DPT ON AG.ASSET_DEPARTMENT = DPT.DEPT_CD ");
		sql.append(" WHERE AG.CMPN_CD = '"+baoCao.getCmpnCd()+"') AS TBL ");

		sql.append(" GROUP BY ASSET_DEPARTMENT, DEPARTMENT_NAME, TBL.GROUP_CD, TBL.GROUP_NAME, TBL.ASSET_MODEL, TBL.ASSET_NAME ");
		System.out.println(sql.toString());
		return sql.toString();
	}
}
