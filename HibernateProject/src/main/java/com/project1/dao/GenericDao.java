package com.project1.dao;

import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T> {
	List<T> getList() throws HibernateException;
	T getById(int id);
	List<T> getByUserId(int id);
	T getByUsername(String username);
	Serializable insert(T t);
	void delete(T t);
}
