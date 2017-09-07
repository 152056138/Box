package test;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.TB.base.quartz.quartzImp.ScheduleService;

public class CronAction {
	private static Scheduler scheduler;  
	private static int step=0;  
	public void setScheduler(@Qualifier("quartzScheduler")Scheduler scheduler) {  
	        this.scheduler = scheduler;  
	}  
	
	public void doSomething() throws Exception {  
        System.out.println("***********************"+step+"***********************");  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String str=df.format(new Date());  
        System.out.println("time:"+str);  
        if(step==1){  
            //满足特定的条件  调用此方法 重设表达式 重新开启新的任务   
            //表达式可以根据需求从配置文件 或者数据库中读取  
            resetJob("0/10 * * * * ?");  
        }  
        step++;  
    }  
	  
	 public static void resetJob(String cronExpression) throws Exception {  
//		 System.out.println("hjhjhj"+scheduler); 
	        CronTriggerBean trigger = (CronTriggerBean) scheduler.getTrigger("cronTrigger", Scheduler.DEFAULT_GROUP);  
	        String oldConExpression = trigger.getCronExpression();   
	        if (!oldConExpression.equalsIgnoreCase(cronExpression)) {  
	            trigger.setCronExpression(cronExpression);  
	            scheduler.rescheduleJob("cronTrigger", Scheduler.DEFAULT_GROUP, trigger);  
	        }  
	    }  
	 
	 public static void main(String[] args) throws Exception {
//		 0 21 18 10 8 ? 2017
				 String cronExpression = " 0 21 18 10 8 ? 2017";
				 
				 ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
				 ScheduleService schedulerService = (ScheduleService)context.getBean("schedulerImpl"); 
				 String job = "";
				 switch("secondComplexJobDetail"){
				 case "secondComplexJobDetail":{job="secondComplexJobDetail";break;}
				 case "firstComplexJobDetail":{job="firstComplexJobDetail";break;}
				 }
				 schedulerService.schedule(job,"seccronTrigger","0 49 16 14 8 ? 2017");
				 
	}
}
