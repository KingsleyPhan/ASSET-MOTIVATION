package com.pts.motivation.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;



public class UtilCommon {

	public static void showMessageError(ModelAndView mv, String message)
	{
		mv.addObject(SessionParams.MESSAGE_ERROR, message);
	}
	
	public static void showMessageNotification(ModelAndView mv, String message)
	{
		mv.addObject(SessionParams.MESSAGE_NOTIFICATION, message);
	}
	
	public static String getSessionValue(HttpServletRequest request, String param)
	{
		return (String)request.getSession().getAttribute(param);
	}
	public static void removeSessionValue(HttpServletRequest request, String param)
	{
		 request.getSession().setAttribute(param,null);
	}
	public static boolean isNotCheckEmpty(String str) {
		if(str != null && str.trim().length()>0)
		{
			return true;
		}
		return false;
	}
	
	public static String ConvertStringToDateStr(String date, String fmt_src, String fmt_des) throws ParseException
	{
//		SimpleDateFormat formatter = new SimpleDateFormat(fmt_src);
//		Date DateConvert = formatter.parse(date);
//		SimpleDateFormat formatter2 = new SimpleDateFormat(fmt_des);  
//	    String strDate = formatter2.format(DateConvert);  
	    
	    SimpleDateFormat sdf1 = new SimpleDateFormat(fmt_src);
	    SimpleDateFormat sdf2 = new SimpleDateFormat(fmt_des);
	    String ds2 = sdf2.format(sdf1.parse(date));
	    
	    return ds2;
		
	}
	
	public static boolean isDateInputRight(String date) {
		if(isEmpty(date)) {
			return false;
		}
		try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
	}
	
	public static boolean isEmpty(String str) {
		if(str == null) 
		{
			return true;
		}
		if(str.trim().length()==0)
		{
			return true;
		}
		return false;
		
	}
	
	public static boolean isNotEmpty(String str) {
		if(str != null && str.trim().length()>0) 
		{
			return true;
		}
		return false;
		
	}
	
	public static String getDateCurrent(String format)
	{
	     Date date = Calendar.getInstance().getTime();  
	     DateFormat dateFormat = new SimpleDateFormat(format);  
	     String strDate = dateFormat.format(date);  
			//System.out.println(strDate);
		 return strDate;
	}
}
