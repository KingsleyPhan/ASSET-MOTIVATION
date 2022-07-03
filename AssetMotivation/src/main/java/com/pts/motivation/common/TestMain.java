package com.pts.motivation.common;



import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpRequest;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class TestMain {
	 public static void main(String args[]) {
		  try {
			  
			  HttpGet httpGet = new HttpGet("http://localhost:8080/Motivation/exportDiChuyenNoiBo?lenh=202107200027929");
			  HttpClient client = HttpClients.createDefault();
			  HttpResponse httpResponse = client.execute(httpGet);
			   
			   
			 String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
			  
			 ConverterProperties converterProperties = new ConverterProperties();

			 FontProvider fontProvider  = new FontProvider();
			 fontProvider.addFont("font-times-new-roman.ttf");
			 fontProvider.addStandardPdfFonts();
			 fontProvider.addSystemFonts(); //for fallback
			 converterProperties.setFontProvider(fontProvider);
			 
			 
			  //HtmlConverter.convertToPdf(new FileInputStream("20210814.html"), new FileOutputStream("string-to-pdf.pdf"));
			  HtmlConverter.convertToPdf(content, new FileOutputStream("string-to-pdf.pdf"), converterProperties);

		     System.out.println( "PDF Created!" );
		  } catch (Exception e) {
			// TODO: handle exception
		}
	  }
}
