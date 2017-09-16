/**
 * session工厂
 * 获得mybatis提供的session对象及相应映射器
 */
package com.TB.base.mybatisUtils;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
        	String resource = "configuration.xml";
            reader    = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 获得SqlSession对象
     * @return
     */
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
        
    }
    
    /**
     * 获得SqlSessionTemplate对象
     */
   public static SqlSessionTemplate getSqlSessionTemplate(){
	  SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	  return sqlSessionTemplate;
   }
}
