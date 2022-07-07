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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
				String dateAsString = "29/11/2021"  ;
				int nbMonths = 4;
			  String format = "dd/MM/yyyy" ;
		        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
		        Date dateAsObj = sdf.parse(dateAsString) ;
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(dateAsObj);
		        cal.add(Calendar.MONTH, nbMonths);
		        Date dateAsObjAfterAMonth = cal.getTime() ;
		    System.out.println(sdf.format(dateAsObjAfterAMonth));
			 
		  } catch (Exception e) {
			// TODO: handle exception
		}
	  }
}
