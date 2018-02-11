package com.elon.date;

import java.util.Date;

import com.elon.date.utility.DateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Date curDate = new Date();
        System.out.println("当前系统时间：" + DateUtil.toLocalDateStr(curDate));
        System.out.println("当前UTC时间：" + DateUtil.toUTCDateString(curDate));
        
        
    }
}
