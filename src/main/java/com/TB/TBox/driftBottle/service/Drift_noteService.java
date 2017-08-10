package com.TB.TBox.driftBottle.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.TB.TBox.driftBottle.bean.Drift_evaluate;
import com.TB.TBox.driftBottle.bean.Drift_note;
import com.TB.TBox.driftBottle.mapper.Drift_noteMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class Drift_noteService implements Drift_noteMapper {
	Logger log = Logger.getLogger(Drift_noteService.class);
	SessionFactory sessionFactory = new SessionFactory();

	// 添加漂流瓶
	public void addDrift_note(Drift_note drift_note) {
		SqlSession session = sessionFactory.getSession();
		try {
			Drift_noteMapper drift_noteOperation = session.getMapper(Drift_noteMapper.class);
			drift_noteOperation.addDrift_note(drift_note);
			session.commit();
		} finally {
			session.close();
		}

	}

	// 添加评论
	public void addDrift_evaluate(Drift_evaluate drift_evaluate) {
		SqlSession session = sessionFactory.getSession();
		try {
			Drift_noteMapper drift_noteOperation = session.getMapper(Drift_noteMapper.class);
			drift_noteOperation.addDrift_evaluate(drift_evaluate);
			session.commit();
		} finally {
			session.close();
		}
	}

	// 修改漂流记录状态
	public void updateDrift_note(Drift_note drift_note) {
		SqlSession session = sessionFactory.getSession();
		try {
			Drift_noteMapper drift_noteOperation = session.getMapper(Drift_noteMapper.class);
			drift_noteOperation.updateDrift_note(drift_note);
			;
			session.commit();
		} finally {
			session.close();
		}
	}

	// 查询某一条漂流记录
	public Drift_note selectDrift_noteByID(int driftId) {
		SqlSession session = sessionFactory.getSession();
		Drift_noteMapper drift_noteOperation = session.getMapper(Drift_noteMapper.class);
		Drift_note drift_note = drift_noteOperation.selectDrift_noteByID(driftId);
		return drift_note;
	}

	// 查询某一条漂流记录的所有评论
	public List<Drift_evaluate> selectAllDrift_note_evaluate(int driftId) {
		SqlSession session = sessionFactory.getSession();
		Drift_noteMapper drift_noteOperation = session.getMapper(Drift_noteMapper.class);
		List<Drift_evaluate> drift_evaluateList = new ArrayList<Drift_evaluate>();
		drift_evaluateList = drift_noteOperation.selectAllDrift_note_evaluate(driftId);
		return drift_evaluateList;
	}

	// 随机抽取某一条漂流记录
	public List<Drift_note> randomSelectDrift_note(int hate) {
		SqlSession session = sessionFactory.getSession();
		Drift_noteMapper drift_noteOperation = session.getMapper(Drift_noteMapper.class);
		List<Drift_note> drift_noteList = new ArrayList<Drift_note>();
		drift_noteList = drift_noteOperation.randomSelectDrift_note(hate);
		return drift_noteList;
	}

}
