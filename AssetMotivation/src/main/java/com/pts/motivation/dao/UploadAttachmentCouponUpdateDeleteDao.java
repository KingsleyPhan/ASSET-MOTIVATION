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

public class UploadAttachmentCouponUpdateDeleteDao {
	
		private CouponAttachmentFile FileAttachment;
		
		public UploadAttachmentCouponUpdateDeleteDao(CouponAttachmentFile FileAttachment) {
			this.FileAttachment = FileAttachment;
		}
		
		public boolean excute() throws SQLException {
			
			DatabaseConnection conn = new DatabaseConnection();
			PreparedStatement  pstm = conn.getConnection().prepareStatement(getSQL()); 
	
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
			sql.append("UPDATE COUPON_ATTACHMENT SET ");
			sql.append("		DELETE_FG = '1' ");
			sql.append("WHERE ");
			sql.append("		ATTACH_ID = '").append(FileAttachment.getId()).append("'");
			System.out.print(sql.toString());
			return sql.toString();
		}
}
