package test;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SecQuartzTest extends QuartzJobBean {
	private Logger log = Logger.getLogger(SecQuartzTest.class);
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("Allowed to launch");
	}

}
