/*
 * 面向user接口实现类
 */
package com.TB.TBox.note.interfaceTo.interfaceToImp;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.TB.TBox.note.bean.Note;
import com.TB.TBox.note.interfaceTo.InterfaceToUser;
import com.TB.TBox.note.mapper.NoteMapper;
import com.TB.TBox.note.service.NoteService;
import com.TB.base.mybatisUtils.SessionFactory;

@Component
public class InterfaceToUserImp implements InterfaceToUser {
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
	 * 根据id查询图片
	 */
	public List<String> sehImage(int noteId) {
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

}
