package com.TB.TBox.push.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.push.bean.PushMsg;
import com.TB.TBox.push.mapper.PushMsgMapper;
import com.TB.TBox.user.service.UserService;
import com.TB.base.mybatisUtils.SessionFactory;
import com.google.gson.Gson;

@Service
public class PushMsgService implements PushMsgMapper {
	Logger log = Logger.getLogger(UserService.class);
	SessionFactory sessionFactory = new SessionFactory();
	Gson gson = new Gson();
	@Autowired
	private PushMsg pushMsg;

	/**
	 * 用户在注册的时候就会返回一个手机固有的id用来为以后推送做准备
	 */
	@Override
	public void addPushMsg(PushMsg pushMsg) {
		SqlSession session = sessionFactory.getSession();
		try {
			PushMsgMapper pushMsgOperation = session.getMapper(PushMsgMapper.class);
			pushMsgOperation.addPushMsg(pushMsg);
			session.commit();
		} finally {
			session.close();
		}

	}

	@Override
	public PushMsg selectPushMsg(int uid) {
		SqlSession session = sessionFactory.getSession();
		PushMsgMapper pushMsgOperation = session.getMapper(PushMsgMapper.class);
		pushMsg = pushMsgOperation.selectPushMsg(uid);
		return pushMsg;
	}

	@Override
	public void updatePushMsg(PushMsg pushMsg) {
		SqlSession session = sessionFactory.getSession();
		try {
			PushMsgMapper pushMsgOperation = session.getMapper(PushMsgMapper.class);
			pushMsgOperation.updatePushMsg(pushMsg);
			session.commit();
		} finally {
			session.close();
		}

	}

	@Test
	public void test() {
		PushMsg pushMsg = new PushMsg();
		PushMsgService pushMsgService = new PushMsgService();
		pushMsg.setUid(2);
		pushMsg.setChannelId("4516889113908580579");
		pushMsgService.addPushMsg(pushMsg);
		
//		pushMsg = pushMsgService.selectPushMsg(1);
//		pushMsgService.updatePushMsg(pushMsg);
//		log.info(gson.toJson(pushMsg));
	}

}
