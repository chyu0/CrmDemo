package com.sqq.crm.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao extends SqlSessionDaoSupport {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public <T> List<T> getList(String key) {
		return getSqlSession().selectList(key);
	}

	public <T> List<T> getList(String key, Object params) {
		return getSqlSession().selectList(key, params);
	}

    public <T> T getOne(String key, Object params) {
        return getSqlSession().selectOne(key, params);
    }

	public void save(String key, Object params) {
		getSqlSession().insert(key, params);
	}

	public void delete(String key, String id) {
		getSqlSession().delete(key, id);
	}

	public void update(String key, Object params) {
		getSqlSession().update(key, params);
	}
}
