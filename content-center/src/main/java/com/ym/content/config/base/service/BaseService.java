package com.ym.content.config.base.service;

import com.ym.content.commons.page.Pager;

import java.util.List;


/**
 * @author Sivan
 */
public interface BaseService<T> {
	public List<T> selectAll();
	public T selectById(int id);
	public T selectByUUID(String uuid);
	public void selectTList(Pager<T> pager);
	public void deleteById(int id);
	public int insert(T t) ;
	public int updateObj(T t);
}
