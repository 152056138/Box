/**
 * note业务逻辑层
 */
package com.TB.TBox.note.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.dataBean.ImageResp;
import com.TB.TBox.note.bean.Good;
import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.mapper.NoteMapper;
import com.TB.base.mybatisUtils.SessionFactory;
import com.TB.base.page.PageBean;

@Service
public class NoteService {
	private SessionFactory sessionFactory;
	private Note note;
	private Logger log = Logger.getLogger(NoteService.class);
	private NoteMapper noteMapper;
	/*
	 * set方法，依赖注入
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setNote(Note note) {
		this.note = note;
	}



	/**
	 * 添加纸条
	 * @param note
	 */
	public void addNote(Note note){
		 SqlSession sqlSession = sessionFactory.getSession();
		 noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteMapper.addNote(note);
			sqlSession.commit();
			log.debug("成功插入");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	/**
	 * 删除纸条
	 * @param noteId
	 */
	public void delNote(int noteId){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteMapper.delNotebyId(noteId);
			sqlSession.commit();
			log.debug("成功删除");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	/**
	 * 按分页查询我的所有字条
	 * @return
	 */
	public PageBean<Note> schMyNoteall(Map<String, Object> val){
		List<Note> noteList = new ArrayList<Note>();
//		SqlSession sqlSession = sessionFactory.getSession();
		SqlSessionTemplate sqlSessionTemplate = sessionFactory.getSqlSessionTemplate();
		noteMapper = sqlSessionTemplate.getMapper(NoteMapper.class);
		PageBean<Note> notePage = new PageBean<Note>();
		try {
			//map形式传入分页查找的数据
			noteList = noteMapper.schMyNoteall(val);
			//查询完成后此方法只需封装查询结果即可，其他分页数据在此不用
			notePage.setDatas(noteList);
			for(Note note : noteList){
				log.debug(note);
			}
		} finally {
			// TODO: handle finally clause
//			sqlSessionTemplate.close();
		}
		return notePage;
	}
	
	/**
	 * 查询某人的note集合
	 * @param uid
	 * @return
	 */
	public List<Note> schSbNoteLim(int uid){
		List<Note> noteList = new ArrayList<Note>();
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteList = noteMapper.schSbNoteall(uid);
			for(Note note : noteList){
				log.debug(note);
			}
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return noteList;
	}
	
	/**
	 * 不按分页查询所有纸条
	 * @return
	 */
	public List<Note> schAllNote(){
		List<Note> noteList = new ArrayList<Note>();
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteList = noteMapper.schAllNote();
			for(Note note : noteList){
				log.debug(note);
			}
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return noteList;
	}
	
	/**
	 * 按id查询
	 * @param noteId
	 * @return
	 */
	public Note schNotebyId(int noteId){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			note = noteMapper.schNotebyId(noteId);
			log.debug(note);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return note;
	}
	/**
	 * 按UID和time查找noteid
	 * @param uid
	 * @param time
	 * @return
	 */
	public int schNote(Map<String, Object> val){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		int noteId ;
		try {
			noteId = noteMapper.schnote(val);
			log.debug(noteId);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return noteId;
	}
	
	/**
	 * 存储图片到仓库
	 * @param image
	 * @return
	 */
	public void addImage(ImageResp image){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteMapper.addpho(image);
			sqlSession.commit();
			log.debug("图片储存成功");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	
	/**
	 * 根据noteid查找图片
	 * @param noteId
	 */
	public List<String> sehImage(int noteId){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		List<String> imageList = new ArrayList<String>();
		try {
			imageList = noteMapper.selImage(noteId);
			log.info("图片数目："+imageList.size());
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return imageList;
	}
	/**
	 * 修改点赞数
	 */
	public void updGoodNum(Map<String, Object> val){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteMapper.updgoodUser(val);
			noteMapper.updgoodNum(val);
			sqlSession.commit();
			log.info("赞一个");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
	/**
	 * 遍历得到相应纸条点赞用户
	 * @param val noteId 和 userNum
	 * @return
	 */
	public Good selgoodUser(Map<String, Object> val){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		Good good = new Good();
		try {
			good = noteMapper.schgoodUser(val);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return good;
	}
	/**
	 * 修改扔鸡蛋数
	 */
	public void updEgg(Map<String, Object> val){
		SqlSession sqlSession = sessionFactory.getSession();
		noteMapper = sqlSession.getMapper(NoteMapper.class);
		try {
			noteMapper.updegg(val);;
			sqlSession.commit();
			log.info("扔一个");
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
	}
}
