package com.elon.date;

import java.text.ParseException;
import java.util.Date;

import com.elon.date.utility.DateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException
    {
    	Date curDate = new Date();
        System.out.println("当前系统时间：" + DateUtil.toLocalDateStr(curDate));
        System.out.println("当前UTC时间：" + DateUtil.toUTCDateString(curDate));
        System.out.println("当前UTC时间：" + DateUtil.getCurrentUTC());
        System.out.println("UTC转换后的当前时间：" + DateUtil.toUTCDate("2018-02-11 21:03:12"));
    }
}
