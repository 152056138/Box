package com.TB.base.quartz.quartzImp;

import java.util.Date;
import java.util.UUID;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SchedulerImpl implements ScheduleService {

	private Scheduler scheduler;  
    private JobDetail jobDetail;  
  
    @Autowired  
    public void setJobDetail( JobDetail jobDetail) {  
        this.jobDetail = jobDetail;  
    }  
  
    @Autowired  
    public void setScheduler( Scheduler scheduler) {  
        this.scheduler = scheduler;  
    }  
  
    public void schedule(String cronExpression) {  
        schedule(null, cronExpression);  
    }  
  
    public void schedule(String name, String cronExpression) {  
        try {  
            schedule(name, new CronExpression(cronExpression));  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    public void schedule(CronExpression cronExpression) {  
        schedule(null, cronExpression);  
    }  
  
    public void schedule(String name, CronExpression cronExpression) {  
        if (name == null || name.trim().equals("")) {  
            name = UUID.randomUUID().toString();  
        }  
  
        try {  
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

//	public void schedule(String name, CronExpression cronExpression) {
//		// TODO Auto-generated method stub
//		
//	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		// TODO Auto-generated method stub
		
	}  
  
    
}
