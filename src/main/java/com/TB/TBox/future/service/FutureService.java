package com.TB.TBox.future.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.driftBottle.service.Drift_noteService;
import com.TB.TBox.future.bean.Future;
import com.TB.TBox.future.mapper.FutureMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class FutureService implements FutureMapper {
	Logger log = Logger.getLogger(Drift_noteService.class);
	SessionFactory sessionFactory = new SessionFactory();

	@Autowired
	private Future future;

	// 添加未来纸条
	public void addFuture(Future future) {
		SqlSession session = sessionFactory.getSession();
		try {
			FutureMapper futureOperation = session.getMapper(FutureMapper.class);
			futureOperation.addFuture(future);
			session.commit();
		} finally {
			session.close();
		}

	}

	// 修改未来纸条状态
	public void updateFutureStatus(Future future) {
		SqlSession session = sessionFactory.getSession();
		try {
			FutureMapper futureOperation = session.getMapper(FutureMapper.class);
			futureOperation.updateFutureStatus(future);
			session.commit();
		} finally {
			session.close();
		}

	}

	// 查询某一条记录
	public Future selectUserFutureNoteById(int aid) {
		SqlSession session = sessionFactory.getSession();
		FutureMapper futureOperation = session.getMapper(FutureMapper.class);
		future = futureOperation.selectUserFutureNoteById(aid);
		return future;
	}

	// 查询某一天推送的全部未来纸条
	public List<Future> selectUserFutureNote(String aend) {
		SqlSession session = sessionFactory.getSession();
		List<Future> futureList = new ArrayList<Future>();
		FutureMapper futureOperation = session.getMapper(FutureMapper.class);
		futureList = futureOperation.selectUserFutureNote(aend);
		return futureList;
	}

}
