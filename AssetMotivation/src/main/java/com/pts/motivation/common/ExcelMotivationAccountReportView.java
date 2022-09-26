package com.pts.motivation.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.pts.motivation.model.MoveObject;

public class ExcelMotivationAccountReportView extends AbstractXlsView {

 @Override
 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
   HttpServletResponse response) throws Exception {
  String nameCode =  UtilCommon.getDateCurrent("yyyyMMddHHmmSS");
  response.setHeader("Content-disposition", "attachment; filename=\"BaoCaoKeToan_"+nameCode+".xls\"");
  
  @SuppressWarnings("unchecked")
  List<MoveObject> list = (List<MoveObject>) model.get("MoveObject");
  
  Sheet sheet = workbook.createSheet("Sheet1");
  
  Row header = sheet.createRow(2);
  header.createCell(0).setCellValue("STT");
  header.createCell(1).setCellValue("MÃ LỆNH");
  header.createCell(2).setCellValue("NGÀY TẠO");
  header.createCell(3).setCellValue("TÊN MÁY");
  header.createCell(4).setCellValue("MODEL MÁY");
  header.createCell(5).setCellValue("SỐ SERI");
  header.createCell(6).setCellValue("MÃ SỐ KẾ TOÁN");
  header.createCell(7).setCellValue("ĐƠN VỊ XUẤT");
  header.createCell(8).setCellValue("ĐƠN VỊ NHẬP");
  header.createCell(9).setCellValue("NGÀY KẾT THÚC");
  header.createCell(10).setCellValue("TRẠNG THÁI");
  header.createCell(11).setCellValue("MÃ KẾ TOÁN DUYỆT");
  									

  int rowNum = 3;
  int STT=1;
  for(MoveObject item : list){
   
   for(int i =0; i<item.getLstAsset().size(); i++) {
	   Row row = sheet.createRow(rowNum++);
	   row.createCell(0).setCellValue(STT++);
	   if(item.getCode().trim().length() > 0)
	   row.createCell(1).setCellValue(item.getNo() +"/"+ item.getCode());
	   else
		   row.createCell(1).setCellValue(item.getNo());

	   row.createCell(2).setCellValue(item.getDateCreate());
	   row.createCell(3).setCellValue(item.getLstAsset().get(i).getName());
	   row.createCell(4).setCellValue(item.getLstAsset().get(i).getModel());
	   row.createCell(5).setCellValue(item.getLstAsset().get(i).getSeries());
	   row.createCell(6).setCellValue(item.getLstAsset().get(i).getAccountCd());
	   row.createCell(7).setCellValue(item.getDeptOutName());
	   row.createCell(8).setCellValue(item.getDeptInName());
	   row.createCell(9).setCellValue(item.getDateIn());
	   if("DA_TRA".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("ĐÃ TRÃ");
	   }
	   if("OK".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("ĐÃ ĐIỀU ĐỘNG");
	   }
	   if("PCD".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("CHỜ PKT DUYỆT");
	   }
	   if("KT".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("CHỜ PKV DUYỆT");
	   }
	   if("NOT_PCD".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("PCD KHÔNG DUYỆT");
	   }
	   if("NOT_KT".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("KT KHÔNG DUYỆT");
	   }
	   if("NOT_KV".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("KV KHÔNG DUYỆT");
	   }
	   if("NEW".equals(item.getStatus())) {
		   row.createCell(10).setCellValue("MỚI");
	   }  
	   row.createCell(11).setCellValue(item.getCommentAccount());
   }
  
 
  }
  
 }

}
