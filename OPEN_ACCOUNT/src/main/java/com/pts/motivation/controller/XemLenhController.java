package com.pts.motivation.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import com.pts.motivation.common.SessionParams;
import com.pts.motivation.common.UtilCommon;
import com.pts.motivation.dao.MoveDetailUpdateDao;
import com.pts.motivation.dao.MoveObjectCountDao;
import com.pts.motivation.dao.MoveObjectDetailInsertDao;
import com.pts.motivation.dao.MoveObjectDetailSelectDao;
import com.pts.motivation.dao.MoveObjectInsertDao;
import com.pts.motivation.dao.MoveObjectSelectDao;
import com.pts.motivation.dao.MoveObjectUpdateDao;
import com.pts.motivation.dao.MoveObjectUpdateDeleteDa;
import com.pts.motivation.dao.SelectTaiSanDao;
import com.pts.motivation.form.TaoLenhDiChuyenNoiBoForm;
import com.pts.motivation.model.ASSET;
import com.pts.motivation.model.AssetMoveObjectDetail;
import com.pts.motivation.model.MoveObject;

@Controller
@RequestMapping("XemLenh")
public class XemLenhController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		String id  = request.getParameter("moveId");
		MoveObject moveOb = new MoveObject();
		if(UtilCommon.isEmpty(id) == false) {
			moveOb.setId(id);
			
			MoveObjectSelectDao moveSelect = new MoveObjectSelectDao(moveOb);
			try {
				List<MoveObject> lstMove = moveSelect.excute();
				if(lstMove.size()==1) {
					model.addAttribute("moveObject", lstMove.get(0));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
		
		
		String numberRow = "0";
		
		MoveObjectDetailSelectDao exAsset = new MoveObjectDetailSelectDao(moveOb);
		
		MoveObject lenhChiTiet;
		try {
			lenhChiTiet = exAsset.excute();
			if(lenhChiTiet.getLstAsset().size()==0) {
				numberRow= "1";
			} else {
				numberRow = lenhChiTiet.getLstAsset().size()+"";
			}
			
			mv.addObject("lstChiTiet", lenhChiTiet.getLstAsset());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mv.addObject("numberRow", numberRow);
		mv.setViewName("XemLenh");
		return mv;
	}
	
	@RequestMapping(params = "export", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response, Model model, @ModelAttribute("moveObject") MoveObject moveObject, BindingResult result) throws IOException {
		
		ModelAndView mv = new ModelAndView();
		
		
		
		MoveObjectSelectDao moveSelect = new MoveObjectSelectDao(moveObject);
		try {
			List<MoveObject> lstMove = moveSelect.excute();
			if(lstMove.size()==1) {
				model.addAttribute("moveObject", lstMove.get(0));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String numberRow = "0";
		
		MoveObjectDetailSelectDao exAsset = new MoveObjectDetailSelectDao(moveObject);
		
		MoveObject lenhChiTiet;
		try {
			lenhChiTiet = exAsset.excute();
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
			  
			  HttpGet httpGet = new HttpGet("http://asset.viettien.com.vn:8081/Motivation/XemLenhExport?lenh="+ moveObject.getId());
			 // HttpGet httpGet = new HttpGet("http://localhost:8080/Motivation/XemLenhExport?lenh="+ moveObject.getId());

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
		      response.getOutputStream().flush();
		      response.getOutputStream().close();
		    } catch (Exception exs) {
		      exs.printStackTrace();
		    }

	
		mv.setViewName("XemLenh");

		
		return mv;
	}
	
	@RequestMapping(params="back" , method = RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request, Model model, @ModelAttribute("moveObject") MoveObject moveObject) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/ChoMuonTrongHeThong");
		return mv;
	}
}
