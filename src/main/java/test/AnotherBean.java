package test;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
@Component("anotherBean")  
public class AnotherBean {  
       
    public void printAnotherMessage(){  
        System.out.println("I am AnotherBean. I am called by Quartz jobBean using CronTriggerFactoryBean");  
    }  
       
}  
