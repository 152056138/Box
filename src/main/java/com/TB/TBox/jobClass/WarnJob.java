/*
 * 提醒字条job类
 */
package com.TB.TBox.jobClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.TB.TBox.note.bean.Warn;
import com.TB.TBox.note.service.WarnService;
import com.TB.TBox.push.bean.PushMsg;
import com.TB.TBox.push.service.PushMsgService;
import com.TB.TBox.user.interfaceTo.ToNodeInterface;
import com.TB.TBox.user.interfaceTo.interfaceToImp.ToNodeImp;
import com.TB.push.PushMsgToSingleDevice;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;

public class WarnJob extends QuartzJobBean{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	public PushMsgToSingleDevice pushMsgToSingleDevice;
	@Autowired
	private PushMsgService pushMsgService;
	@Autowired
	private WarnService warnService;
	@Autowired
	private PushMsg pushMsg;
	ToNodeInterface toNodeI = new ToNodeImp();
	@Override
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//获取与定时器触发相同的时间（有误差没关系我们是按天模糊查询）
		Date date = new Date();
		String time = sdf.format(date);
		//获取到某一天要推送的提醒
		List<Warn> warnList = warnService.sehWarn(time);
		//遍历推送
		for(Warn warn : warnList){
			//得到json格式的massage
			String message = warnService.setMessage(warn);
			//获得接收者（被推送人）的id
			int uid = toNodeI.selectFriendUid(warn.getWto());
			
			pushMsg = pushMsgService.selectPushMsg(uid);
			String channelId = pushMsg.getChannelId();
			try {
				pushMsgToSingleDevice.getpushMsg(message, channelId);
			} catch (PushClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PushServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//调用了这个方法以后会修改提醒记录为调用状态
				warn.setStatus(1);
				warnService.updateWarn(warn.getWid());
			}
		}
	}
	
}
