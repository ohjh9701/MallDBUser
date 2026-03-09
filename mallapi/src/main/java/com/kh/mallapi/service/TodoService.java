package com.kh.mallapi.service;

import com.kh.mallapi.dto.TodoDTO;

public interface TodoService {
	//insert
	public Long register(TodoDTO todoDTO);
	//select
	public TodoDTO get(Long tno);
	//update
	void modify(TodoDTO todoDTO);
	//delete
	void remove(Long tno); 
}
