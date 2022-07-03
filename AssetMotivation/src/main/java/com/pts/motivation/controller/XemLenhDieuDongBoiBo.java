package com.pts.motivation.controller;

import java.awt.event.ItemEvent;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import com.pts.motivation.common.GetMap;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.InsertLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.InsertTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.SelectDepartmentDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.SelectDiChuyenNoiBoDao;
import com.pts.motivation.dao.SelectMotivationCountDao;
import com.pts.motivation.dao.SelectTaiSanDao;
import com.pts.motivation.dao.UpdateCommentCreateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.dao.UpdateLenhDiChuyenNoiBoChiTietDao;
import com.pts.motivation.dao.UpdateTaoLenhDiChuyenNoiBoDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.ASSET;
import com.pts.motivation.model.ASSETDICHUYENNOIBO;
import com.pts.motivation.model.DEPARTMENT_S;
import com.pts.motivation.model.LENHDICHUYENNOIBO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("XemLenhDieuDong")
public class XemLenhDieuDongBoiBo {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		String CouponCd = request.getParameter("lenh");
		String numberRow = "0";
		if(UtilCommon.isEmpty(CouponCd)) {
			mv.setViewName("redirect:/DiChuyenNoiBo");
		} else {
			LENHDICHUYENNOIBO lenh = new LENHDICHUYENNOIBO();
			lenh.setId(CouponCd);
			SelectDiChuyenNoiBoDao ex = new SelectDiChuyenNoiBoDao(lenh);
			
			try {
				List<LENHDICHUYENNOIBO> lst = ex.excute();
				if(lst.size()==1) {
					TaoLenhDiChuyenNoiBoForm TaoLenhDiChuyenNoiBoForm = new TaoLenhDiChuyenNoiBoForm();
					TaoLenhDiChuyenNoiBoForm.setId(lst.get(0).getId().trim());		
					TaoLenhDiChuyenNoiBoForm.setNoNumber(lst.get(0).getNoNumber());
					TaoLenhDiChuyenNoiBoForm.setSticker(lst.get(0).getSticker());
					TaoLenhDiChuyenNoiBoForm.setDeptIn(lst.get(0).getDeptIn().trim());
					TaoLenhDiChuyenNoiBoForm.setDeptOut(lst.get(0).getDeptOut().trim());
					TaoLenhDiChuyenNoiBoForm.setDateOut(lst.get(0).getDateStart().trim());
					TaoLenhDiChuyenNoiBoForm.setReason(lst.get(0).getReason());
					TaoLenhDiChuyenNoiBoForm.setCommentCreate(lst.get(0).getCommentCreate());
					model.addAttribute("TaoLenhDiChuyenNoiBoForm", TaoLenhDiChuyenNoiBoForm);
				}
				SelectDiChuyenNoiBoChiTietDao exAsset = new SelectDiChuyenNoiBoChiTietDao(lenh);
				
				LENHDICHUYENNOIBO lenhChiTiet = exAsset.excute();
				
				if(lenhChiTiet.getLstAsset().size()==0) {
					numberRow= "1";
				} else {
					numberRow = lenhChiTiet.getLstAsset().size()+"";
				}
				
				mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
				mv.addObject("numberRow", numberRow);
			
				
				
				
				
				mv.setViewName("XemLenhDieuDongNoiBo");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return mv;
	}
	
	
	
	@Autowired
	  private ServletContext context;
	
	@RequestMapping(params = "export", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response, Model model, @ModelAttribute("TaoLenhDiChuyenNoiBoForm") TaoLenhDiChuyenNoiBoForm form, BindingResult result) throws IOException {
		
		ModelAndView mv = new ModelAndView();
		ShowDataCommon(model);
		
		LENHDICHUYENNOIBO lenh = new LENHDICHUYENNOIBO();
		lenh.setId(form.getId());
		SelectDiChuyenNoiBoDao ex = new SelectDiChuyenNoiBoDao(lenh);
		
		try {
			List<LENHDICHUYENNOIBO> lst = ex.excute();
			if(lst.size()==1) {
				TaoLenhDiChuyenNoiBoForm TaoLenhDiChuyenNoiBoForm = new TaoLenhDiChuyenNoiBoForm();
				TaoLenhDiChuyenNoiBoForm.setId(lst.get(0).getId().trim());		
				TaoLenhDiChuyenNoiBoForm.setNoNumber(lst.get(0).getNoNumber());
				TaoLenhDiChuyenNoiBoForm.setSticker(lst.get(0).getSticker());
				TaoLenhDiChuyenNoiBoForm.setDeptIn(lst.get(0).getDeptIn().trim());
				TaoLenhDiChuyenNoiBoForm.setDeptOut(lst.get(0).getDeptOut().trim());
				TaoLenhDiChuyenNoiBoForm.setDateOut(lst.get(0).getDateStart().trim());
				TaoLenhDiChuyenNoiBoForm.setReason(lst.get(0).getReason());
				TaoLenhDiChuyenNoiBoForm.setCommentCreate(lst.get(0).getCommentCreate());
				model.addAttribute("TaoLenhDiChuyenNoiBoForm", TaoLenhDiChuyenNoiBoForm);
			}
			SelectDiChuyenNoiBoChiTietDao exAsset = new SelectDiChuyenNoiBoChiTietDao(lenh);
			
			LENHDICHUYENNOIBO lenhChiTiet = exAsset.excute();
			String numberRow = "";
			if(lenhChiTiet.getLstAsset().size()==0) {
				numberRow= "1";
			} else {
				numberRow = lenhChiTiet.getLstAsset().size()+"";
			}
			
			mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
			mv.addObject("numberRow", numberRow);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		 String pad= "";
		try {
			  
			  HttpGet httpGet = new HttpGet("http://localhost:8080/Motivation/exportDiChuyenNoiBo?lenh="+form.getId());
			  HttpClient client = HttpClients.createDefault();
			  HttpResponse httpResponse = client.execute(httpGet);
			   
			   
			 String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
			  
			 ConverterProperties converterProperties = new ConverterProperties();

			 FontProvider fontProvider  = new FontProvider();
			 fontProvider.addFont("font-times-new-roman.ttf");
			 fontProvider.addStandardPdfFonts();
			 fontProvider.addSystemFonts(); //for fallback
			 converterProperties.setFontProvider(fontProvider);
			 
			 ServletContext context = request.getSession().getServletContext();  
			    String path = context.getRealPath("/"); 
			 
			 pad= path + File.separator + UtilCommon.getDateCurrent(SessionParams.OBJECT_CD)+ ".pdf";
			 FileOutputStream filepdf = new FileOutputStream(pad);
			  //HtmlConverter.convertToPdf(new FileInputStream("20210814.html"), new FileOutputStream("string-to-pdf.pdf"));
			  HtmlConverter.convertToPdf(content,filepdf, converterProperties);
			  
			  
			  
		     System.out.println( "PDF Created!" );
		  } catch (Exception e) {
			// TODO: handle exception
		}
			
		 try {
		      File file = new File(pad);
		      byte[] data = FileUtils.readFileToByteArray(file);
		      // Thiết lập thông tin trả về
		      response.setContentType("application/octet-stream");
		      response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
		      response.setContentLength(data.length);
		      InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
		      FileCopyUtils.copy(inputStream, response.getOutputStream());
		    } catch (Exception exs) {
		      exs.printStackTrace();
		    }

	
		mv.setViewName("XemLenhDieuDongNoiBo");

		
		return mv;
	}
	
	
	@RequestMapping(params="back", method = RequestMethod.POST)
	public String back() {
		return "redirect:/DiChuyenNoiBo";
	}
	
	public void ShowDataCommon(Model model) {
		SelectDepartmentDao ex = new SelectDepartmentDao("202012151019454");
		try {
			List<DEPARTMENT_S> LISTDEPT = ex.excute();
			model.addAttribute("lstDept", new GetMap().getMapDepartment(LISTDEPT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void downloadFile3(String filePath, String urlDownload) throws ClientProtocolException, IOException {
	    File fileDownload = new File(filePath);
	    HttpGet httpGet = new HttpGet(urlDownload);
	    HttpClient client = HttpClients.createDefault();
	    HttpResponse httpResponse = client.execute(httpGet);
	    InputStream is = httpResponse.getEntity().getContent();
	    FileOutputStream fos = new FileOutputStream(fileDownload);
	    int inByte;
	    while ((inByte = is.read()) != -1) {
	      fos.write(inByte);
	    }
	    is.close();
	    fos.close();
	  }

}
