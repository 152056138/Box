/**
 * 评回类业务逻辑
 */
package com.TB.TBox.note.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.note.bean.Evaluate;
import com.TB.TBox.note.mapper.EvaluateMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class EvaluateService {
	private SessionFactory sessionFactory;
	private Evaluate evaluate;
	private Logger log = Logger.getLogger(EvaluateMapper.class);
	private SqlSession sqlSession = sessionFactory.getSession();
	private EvaluateMapper evaluateMapper = sqlSession.getMapper(EvaluateMapper.class);
	
	
	/*
	 * set上的注解注入
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Autowired
	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}
	/*
	 * 显示某字条所有评回
	 */
	public List<Evaluate> showEva(){
		List<Evaluate> evaluateList = new ArrayList<Evaluate>();
		try {
			evaluateList = evaluateMapper.selEva();
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return evaluateList;
	}
	
	/*
	 * 写评价或回复
	 */
	public void addEva(){
		try {
			evaluateMapper.addEva();
			sqlSession.commit();
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	/*
	 * 删除评价或回复
	 */
	public void delEva(int eid){
		try {
			evaluateMapper.delEva(eid);
			sqlSession.commit();
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
}
