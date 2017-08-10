import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AnotherBean extends QuartzJobBean {
	Logger log = Logger.getLogger(AnotherBean.class);
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.warn("Gravity anomaly!");
	}
	
}
