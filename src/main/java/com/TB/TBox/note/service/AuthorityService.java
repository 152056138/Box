/**
 * 权限类逻辑层
 */
package com.TB.TBox.note.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.note.bean.Authority;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.mapper.AuthorityMapper;
import com.TB.TBox.note.mapper.NoteMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class AuthorityService {
	private SessionFactory sessionFactory;
	private Authority Authority;
	private Logger log = Logger.getLogger(AuthorityService.class);
	private AuthorityMapper authorityMapper;
	
	//set注入
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public void setAuthority(Authority authority) {
		Authority = authority;
	}
	
	/**
	 * 设置权限
	 * @param authorityList
	 */
	public void setAut(Authority authorityList){
		SqlSession sqlSession = sessionFactory.getSession();
		authorityMapper = sqlSession.getMapper(AuthorityMapper.class);
		try {
			authorityMapper.addAut(authorityList);
			sqlSession.commit();
			log.info("设置权限成功");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	/**
	 * 查询用户与字条的权限关系
	 * @param noteId
	 * @return
	 */
	public List<Authority> schAutByid(int noteId){
		SqlSession sqlSession = sessionFactory.getSession();
		authorityMapper = sqlSession.getMapper(AuthorityMapper.class);
		List<Authority> authorityList = new ArrayList<Authority>();
		try {
			authorityList=authorityMapper.schAut(noteId);
			log.info("查询成功");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return authorityList;
	}
}
