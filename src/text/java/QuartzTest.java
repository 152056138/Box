import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.TB.base.quartz.QuartzThreadPool;

@Component("quartzTest")
public class QuartzTest extends QuartzJobBean {
	private Logger log = Logger.getLogger(QuartzTest.class);
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.debug("going to be started..");
		System.out.println("going to be started..");
		Scheduler scheduler=null;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			scheduler.shutdown(true);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}
	public static void main(String[] args) {
		
	
		// TODO Auto-generated method stub
		ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext.xml");
		test.SecQuartzTest secQuartzTest =  (test.SecQuartzTest) a.getBean("secQuartzTest");
		secQuartzTest.setAid(10);
		QuartzThreadPool q = new QuartzThreadPool();
		q.setText("secondComplexJobDetail", "2017-9-7 20:20:00");
	}
	
}
