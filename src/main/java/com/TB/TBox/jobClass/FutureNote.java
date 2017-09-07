package com.TB.TBox.jobClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.TB.TBox.future.bean.Future;
import com.TB.TBox.future.service.FutureService;
import com.TB.TBox.push.bean.PushMsg;
import com.TB.TBox.push.service.PushMsgService;
import com.TB.push.PushMsgToSingleDevice;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
@Component
public class FutureNote extends QuartzJobBean {
	DateFormat mimate = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private Future future;
	@Autowired
	private FutureService futureService;
	@Autowired
	private PushMsg pushMsg;
	@Autowired
	private PushMsgService pushMsgService;
	@Autowired
	private PushMsgToSingleDevice pushMsgToSingleDevice;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		/**
		 * 整个传输过程
		 * @throws PushServerException 
		 * @throws PushClientException 
		 */
			Date date = new Date();
			String aend= mimate.format(date);
			System.out.println(aend);
			List<Future> futureList = new ArrayList<Future>();
			futureList = futureService.selectUserFutureNote(aend);
			for(Future future : futureList){
				pushMsg = pushMsgService.selectPushMsg(future.getAfrom());
				String msg = futureService.setMessage(future);
				try {
					try {
						pushMsgToSingleDevice.getpushMsg(msg,pushMsg.getChannelId());
					} catch (PushClientException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch ( PushServerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				future.setAstatus(1);
				futureService.updateFutureStatus(future);
			}
			
		}
	}

