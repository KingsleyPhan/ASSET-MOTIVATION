package com.pts.motivation.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.itextpdf.io.util.IdelOutputStream;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.UploadAttachmentCouponInsertDao;
import com.pts.motivation.dao.UploadAttachmentCouponUpdateDeleteDao;
import com.pts.motivation.model.CouponAttachmentFile;

@RestController
public class APIUploadAttachmentCoupon {

	public String KEY_ACTION_UPLOAD = "U";
	public String KEY_ACTION_DEL = "D";

	@PostMapping(value = "UploadAttachmentFile")
	public JsonObject UploadAttachmentFileCoupon(@RequestParam(value = "file", required=false) MultipartFile file,
			@RequestParam("couponCd") String CouponCd, @RequestParam("userCd") String userCd,
			@RequestParam("attachId") String attachId, @RequestParam("action") String action,
			HttpServletRequest request) {
		if (KEY_ACTION_UPLOAD.equals(action)) {

			String uploadsDir = "/uploads/";
			String realPathtoUploads = "C://AMS_ATTACHMENT_FILE//";
			if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
			}


			String orgName = UtilCommon.getDateCurrent("yyyyMMddHHmmSS") + "_"
					+ file.getOriginalFilename().replaceAll(" ", "");
			
			String filePath = realPathtoUploads + orgName;
			File dest = new File(filePath);

			System.out.println("realPathtoUploads = " + filePath);

			try {
				file.transferTo(dest);
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			CouponAttachmentFile fileAttachment = new CouponAttachmentFile();
			fileAttachment.setId(UtilCommon.getDateCurrent("YYYYmmDDHHmmss"));
			fileAttachment.setFileName(orgName);
			fileAttachment.setCouponcd(CouponCd);
			fileAttachment.setUserCd(userCd);
			fileAttachment.setInsertDt(UtilCommon.getDateCurrent("dd/MM/yyyy"));

			UploadAttachmentCouponInsertDao insert = new UploadAttachmentCouponInsertDao(fileAttachment);
			try {
				insert.excute();
				
				String uri = request.getScheme() + "://" +   
			             request.getServerName() +       
			             ":" + request.getServerPort() + 
			             request.getRequestURI();       

				JsonObject result = new JsonObject();
				result.addProperty("success", true);
				result.addProperty("id", fileAttachment.getId());
				result.addProperty("urlFile", uri + "?FileName="+orgName);
				result.addProperty("date", UtilCommon.getDateCurrent("dd/MM/yyyy"));
				return result;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (KEY_ACTION_DEL.equals(action)) {
			CouponAttachmentFile fileAttachment = new CouponAttachmentFile();
			fileAttachment.setId(attachId);
			
			UploadAttachmentCouponUpdateDeleteDao delete = new  UploadAttachmentCouponUpdateDeleteDao(fileAttachment);
			try {
				delete.excute();
				JsonObject result = new JsonObject();
				result.addProperty("success", true);
				result.addProperty("id", fileAttachment.getId());
				result.addProperty("date", UtilCommon.getDateCurrent("dd/MM/yyyy"));
				
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return null;
	}

	@RequestMapping(value = "/UploadAttachmentFile", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> pdfDownload(
	        HttpServletRequest request
	) throws IOException
	{
		
		String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
        String pathChild= "C://AMS_ATTACHMENT_FILE//";
	    String path = pathChild  + request.getParameter("FileName");
	    File file = new File(path);
	    HttpHeaders httpHeaders = new HttpHeaders();
	    String fileName = file.getName();
	    httpHeaders.setContentDispositionFormData("attachment",java.net.URLEncoder.encode(fileName,"UTF-8"));
	    httpHeaders.setContentType(MediaType.parseMediaType("application/pdf"));
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
	            httpHeaders,
	            HttpStatus.CREATED);
	}

}
