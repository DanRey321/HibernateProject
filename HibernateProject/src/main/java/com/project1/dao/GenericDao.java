package com.project1.dao;

import com.project1.model.Reimbursement;
import com.project1.model.User;

import java.util.List;

public interface GenericDao <T> {
	List<T> getList();
	T getById(int id);
	List<T> getByUserId(int id);
	T getByUsername(String username);
	void insert(T t);
	boolean delete(T t);
}
