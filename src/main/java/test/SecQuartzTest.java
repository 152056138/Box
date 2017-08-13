package test;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
@Component
public class SecQuartzTest extends QuartzJobBean {
private int aid;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public void aid(){
		System.out.println(aid);
	}
	private Logger log = Logger.getLogger(SecQuartzTest.class);
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		log.info("Allowed to launch");
		System.out.println("aid:"+aid);
	}

}
