/*
 * quartz工具类
 */
package com.TB.base.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.TB.base.quartz.quartzImp.ScheduleService;

public class QuartUtils {
	/**
	 * 设置定时任务时间，并调用
	 * 传入参数job为要执行的函数名，time为设置的执行时间
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	private void settime(String jobClass,String time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//得到时分秒年月日
		int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int hour=cal.get(Calendar.HOUR);//小时
        int minute=cal.get(Calendar.MINUTE);//分           
        int second=cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        //获得cron
        String cron = second+" "+minute+" "+hour+" "+day+" "+month+" ? "+year;
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ScheduleService schedulerService = (ScheduleService)context.getBean("schedulerImpl");
        //获得job类，触发器的name,动态的操控定时器
        String job = "";
		 switch(jobClass){
		 case "secondComplexJobDetail":{job="secondComplexJobDetail";break;}
		 case "firstComplexJobDetail":{job="firstComplexJobDetail";break;}
		 }
		 schedulerService.schedule(job,job+"Trigger",cron);
	}
	
	
}
