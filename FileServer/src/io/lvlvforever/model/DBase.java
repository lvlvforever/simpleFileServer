package io.lvlvforever.model;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBase {
	private static SqlSessionFactory factory = null;
	public DBase(){
		if(factory != null)
			return;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	protected  SqlSession openSession() {
		return factory.openSession();
	}
}
