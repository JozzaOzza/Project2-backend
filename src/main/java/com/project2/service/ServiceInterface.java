package com.project2.service;

import java.util.List;

public interface ServiceInterface<T>{

	T create(T t);
	
	List<T> getAll();
	
	T getOne(Long id);
	
	T update(Long id, T t);
	
	void remove(Long id);
	
}
