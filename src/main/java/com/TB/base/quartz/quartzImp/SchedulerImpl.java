package com.TB.base.quartz.quartzImp;

import java.util.Date;
import java.util.UUID;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SchedulerImpl implements ScheduleService {
	
	private Scheduler scheduler;  
	//测试用例
    private JobDetail firstComplexJobDetail;
    private JobDetail secondComplexJobDetail;
    //未来纸条定时任务
    private JobDetail FutureNote;
    //提醒纸条定时任务
    private JobDetail WarnJob;
  
  

    
    public void setFutureNote(@Qualifier("FutureNote")JobDetail futureNote) {
		FutureNote = futureNote;
	}


	public void setWarnJob(@Qualifier("WarnJob")JobDetail warnJob) {
		WarnJob = warnJob;
	}


	public void setSecondComplexJobDetail(@Qualifier("secondComplexJobDetail")JobDetail jobDetail) {  

        this.secondComplexJobDetail = jobDetail;  
    }  
    

    public void setFirstComplexJobDetail(@Qualifier("firstComplexJobDetail")JobDetail jobDetail) {  
        this.firstComplexJobDetail = jobDetail;  
    }  
  
    public void setScheduler(@Qualifier("schedulerFactory")Scheduler scheduler) {  
        this.scheduler = scheduler;  
    }  
  
    
    public void schedule(String cronExpression) {  
        schedule(null,null, cronExpression);  
    }  
  
    public void schedule(String job,String name, String cronExpression) {  
        try {  
            schedule(job,name, new CronExpression(cronExpression));  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    public void schedule(CronExpression cronExpression) {  
//        schedule(null, cronExpression);  
    }  
  
    public void schedule(String job,String name, CronExpression cronExpression) {  
    	JobDetail jobDetail = null;
        if (name == null || name.trim().equals("")) {  
            name = UUID.randomUUID().toString();  
        }  
  
        try {  
        	switch(job){
        	case "firstComplexJobDetail":{jobDetail = firstComplexJobDetail;break;}
        	case "secondComplexJobDetail":{jobDetail = secondComplexJobDetail;break;}
        	case "WarnJob":{jobDetail = WarnJob;break;}
        	case "FutureNote":{jobDetail = FutureNote;break;}
        	}
            scheduler.addJob(jobDetail, true);  
  
            CronTrigger cronTrigger = new CronTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail.getName(),  
                    Scheduler.DEFAULT_GROUP);  
            cronTrigger.setCronExpression(cronExpression);  
            scheduler.scheduleJob(cronTrigger);  
            scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, cronTrigger);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    public void schedule(Date startTime) {  
        schedule(startTime, null);  
    }  
  
    public void schedule(String name, Date startTime) {  
        schedule(name, startTime, null);  
    }  
  
    public void schedule(Date startTime, Date endTime) {  
        schedule(startTime, endTime, 0);  
    }  
  
    public void schedule(String name, Date startTime, Date endTime) {  
        schedule(name, startTime, endTime, 0);  
    }  
  
    public void schedule(Date startTime, Date endTime, int repeatCount) {  
        schedule(null, startTime, endTime, 0);  
    }  
  
    public void schedule(String name, Date startTime, Date endTime, int repeatCount) {  
        schedule(name, startTime, endTime, 0, 0L);  
    }  
  
    public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) {  
        schedule(null, startTime, endTime, repeatCount, repeatInterval);  
    }



    public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {  
//        if (name == null || name.trim().equals("")) {  
//            name = UUID.randomUUID().toString();  
//        }  
//  
//        try {  
//            scheduler.addJob(jobDetail, true);  
//  
//            SimpleTrigger SimpleTrigger = new SimpleTrigger(name, Scheduler.DEFAULT_GROUP, jobDetail.getName(),  
//                    Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval);  
//            scheduler.scheduleJob(SimpleTrigger);  
//            scheduler.rescheduleJob(name, Scheduler.DEFAULT_GROUP, SimpleTrigger);  
//  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
    }  
  
    
}
