package com.elon;

import java.util.Calendar;

public class StaruptService
{
	public static void main(String[] args)
	{
//		try
//		{
//			FileNoExistException e = throwException();
			
			StringBuffer UTCTimeBuffer = new StringBuffer();
	        // 1、取得本地时间：
	        Calendar cal = Calendar.getInstance() ;
	        // 2、取得时间偏移量：
	        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
	        // 3、取得夏令时差：
	        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
	        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
	        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH)+1;
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE); 
	        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
	        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute) ;
	        
	        System.out.println(UTCTimeBuffer.toString());
//	        try{
//	            format.parse(UTCTimeBuffer.toString()) ;
//	            return UTCTimeBuffer.toString() ;
//	        }catch(ParseException e)
//	        {
//	            e.printStackTrace() ;
//	        }
//	        return null ;
//		} 
//		catch (FileNoExistException e)
//		{
//			System.out.println(e.getExceptionMsg());
//		}
	}
	
	private static FileNoExistException throwException() throws FileNoExistException
	{
		throw new FileNoExistException("D:/123.xlsx");
	}
}
