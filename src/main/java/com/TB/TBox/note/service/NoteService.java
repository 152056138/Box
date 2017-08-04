/**
 * note业务逻辑层
 */
package com.TB.TBox.note.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.mapper.NoteMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class NoteService {
	private SessionFactory sessionFactory;
	private Note note;
	private Logger log = Logger.getLogger(NoteService.class);
	private SqlSession sqlSession = sessionFactory.getSession();
	private NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
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
	 * 查询所有
	 * @return
	 */
	public List<Note> schNoteall(){
		List<Note> noteList = new ArrayList<Note>();
		try {
			noteList = noteMapper.schNoteall();
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
		try {
			note = noteMapper.schNotebyId(noteId);
			log.debug(note);
		} finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return note;
	}
}
