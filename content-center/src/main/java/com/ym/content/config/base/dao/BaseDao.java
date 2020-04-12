package com.ym.content.config.base.dao;

import java.util.List;

import com.ym.content.commons.page.Pager;

public interface BaseDao<T> {
	
	public List<T> selectAll();
	public T selectById(int id);
	public T selectByUUID(String uuid);
	
	public List<T> selectTList(Pager<T> pager);
	public int selectTListCount(Pager<T> pager);	
	
	public void deleteById(int id);
	
	public int insert(T t);
	
	public int updateObj(T t);
}
