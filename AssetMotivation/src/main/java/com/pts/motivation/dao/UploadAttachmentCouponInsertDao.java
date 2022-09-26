package com.pts.motivation.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pts.motivation.common.DatabaseConnection;
import com.pts.motivation.model.CouponAttachmentFile;
import com.pts.motivation.model.DEPARTMENT_S;

import com.pts.motivation.model.MoveObject;

public class UploadAttachmentCouponInsertDao {
	
		private CouponAttachmentFile FileAttachment;
		
		public UploadAttachmentCouponInsertDao(CouponAttachmentFile FileAttachment) {
			this.FileAttachment = FileAttachment;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
			pstm.setString(1, FileAttachment.getId());
			pstm.setString(2, FileAttachment.getFileName());
			pstm.setString(3, FileAttachment.getCouponcd());
			pstm.setString(4, FileAttachment.getUserCd());
			pstm.setString(5, FileAttachment.getInsertDt());
			pstm.setString(6, "0");

	
			pstm.executeUpdate();
			return true;
		}
		
	//	CREATE TABLE [dbo].[COUPON_ATTACHMENT] (
	//		    [ATTACH_ID]   NVARCHAR (MAX) NOT NULL,
	//		    [ATTACH_NAME] NVARCHAR (MAX) NULL,
	//		    [COUPON_CD]   NVARCHAR (MAX) NULL,
	//		    [USER_INSERT] NVARCHAR (MAX) NULL,
	//		    [INSERT_DT]   NVARCHAR (MAX) NULL,
	//		    [USER_UPDATE] NVARCHAR (MAX) NULL,
	//		    [UPDATE_DT]   NVARCHAR (MAX) NULL,
	//		    [DELETE_FG]   NVARCHAR (1)   NULL,
	//		    CONSTRAINT [PK_COUPON_ATTACHMENT] PRIMARY KEY CLUSTERED ([ATTACH_ID] ASC)
	//		);
		public String getSQL() {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO COUPON_ATTACHMENT ( ");
			sql.append("		ATTACH_ID, ");
			sql.append("		ATTACH_NAME, ");
			sql.append("		COUPON_CD, ");
			sql.append("		USER_INSERT, ");
			sql.append("		INSERT_DT, ");
			sql.append("		DELETE_FG ");
			sql.append(") VALUES ( ");
			sql.append("		? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append("		,? ");
			sql.append(") ");
			return sql.toString();
		}
}
