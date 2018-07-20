package com.sduwh.foodcompany.comm;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	public static SqlSession getSession() {
		//mybatis�������ļ�
        String resource = "SqlConfigMap.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis������.�ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        SqlSession session = sessionFactory.openSession();
        return session;
	}
}
