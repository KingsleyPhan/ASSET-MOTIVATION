package com.pts.motivation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.CouponAttachmentFile;
import com.pts.motivation.model.USER;

public class CouponAttachmentSelectDao {
	private String  attachId; 
	private String downloadUrl;
	
	public CouponAttachmentSelectDao(String  attachId, String downloadUrl) {
		this.attachId = attachId;
		this.downloadUrl = downloadUrl;
	}
	
	public List<CouponAttachmentFile> excute() throws SQLException {
		List<CouponAttachmentFile> lst = new ArrayList<CouponAttachmentFile>();
		DatabaseConnection conn = new  DatabaseConnection();
		Connection connEx = conn.getConnection();
		Statement stm = connEx.createStatement();
	
		ResultSet rs = stm.executeQuery(getSQL());
		
		while(rs.next()) {
			CouponAttachmentFile item = new CouponAttachmentFile();
			item.setId(rs.getString("ATTACH_ID"));
			item.setFileName(rs.getString("ATTACH_NAME"));
			item.setCouponcd(rs.getString("COUPON_CD"));
			item.setUserCd(rs.getString("USER_NAME"));
			item.setInsertDt(rs.getString("INSERT_DT"));
			item.setDownloadUrl(downloadUrl+ item.getFileName());
			lst.add(item);
		}
		return lst;
	}
	
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(" SELECT ");
		sql.append(" ATTACH_ID,");
		sql.append(" ATTACH_NAME,");
		sql.append(" COUPON_CD, ");
		sql.append(" US.USER_NAME,");
		sql.append(" CA.INSERT_DT");

		sql.append(" FROM COUPON_ATTACHMENT CA LEFT JOIN USER_SYSTEM  US ON CA.USER_INSERT = US.USER_EMPLOYEE_CD");
		sql.append(" WHERE ");
		sql.append(" COUPON_CD = ").append("'" +this.attachId+"'");
		sql.append(" AND DELETE_FG = '0' ");
		
		
		
		System.out.println(sql.toString());
		
		return sql.toString();
	}
}
