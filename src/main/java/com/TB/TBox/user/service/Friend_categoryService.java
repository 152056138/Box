package com.TB.TBox.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;

import com.TB.TBox.user.bean.Friend_category;
import com.TB.TBox.user.mapper.Friend_categoryMapper;
import com.TB.base.mybatisUtils.SessionFactory;

@Service
public class Friend_categoryService implements Friend_categoryMapper {

	Logger log = Logger.getLogger(UserService.class);
	SessionFactory sessionFactory = new SessionFactory();

	public void addFriend_category(Friend_category friend_category) {
		SqlSession session = sessionFactory.getSession();
		try {
			Friend_categoryMapper categoryOperation = session.getMapper(Friend_categoryMapper.class);
			categoryOperation.addFriend_category(friend_category);
			session.commit();
			System.out.println("当前增加的分类 id为:" + friend_category.getCid());
		} finally {
			session.close();
		}

	}

	public List<Friend_category> selectAllFriend_category() {
		SqlSession session = sessionFactory.getSession();
		List<Friend_category> categoryList = new ArrayList<Friend_category>();
		try {
			Friend_categoryMapper categoryOperation = session.getMapper(Friend_categoryMapper.class);
			categoryList = categoryOperation.selectAllFriend_category();
			for (Friend_category category : categoryList) {
				System.out.println(category.toJson());
			}
		} finally {
			// TODO: handle finally clause
		}
		return categoryList;
	}

	@Test
	public void test() {
		Friend_categoryService c = new Friend_categoryService();
		// Friend_category category = new Friend_category();
		// category.setCategory("朋友");
		// c.addFriend_category(category);
		// log.info(category.toJson());

		List<Friend_category> clist = c.selectAllFriend_category();
	}
}
