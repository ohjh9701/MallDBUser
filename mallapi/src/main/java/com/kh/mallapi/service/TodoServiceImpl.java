package com.kh.mallapi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kh.mallapi.domain.Todo;
import com.kh.mallapi.dto.TodoDTO;
import com.kh.mallapi.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
//final로 선언된 필드(modelMapper, todoRepository)에 대해 자동 생성자주입 
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	// 자동주입 대상은 final로 해야한다.
	private final ModelMapper modelMapper;
	private final TodoRepository todoRepository;

	@Override
	public Long register(TodoDTO todoDTO) {
		log.info("......................... ");
		// TodoDTO를 Todo로 변환
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		Todo savedTodo = todoRepository.save(todo);

		return savedTodo.getTno();
	}

	@Override
	public TodoDTO get(Long tno) {
		log.info("......................... ");
		java.util.Optional<Todo> result = todoRepository.findById(tno);
		Todo todo = result.orElseThrow();

		TodoDTO todoDTO = modelMapper.map(todo, TodoDTO.class);
		return todoDTO;
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
		Todo todo = result.orElseThrow();
		
		todo.changeTitle(todoDTO.getTitle());
		todo.changeDueDate(todoDTO.getDueDate());
		todo.changeComplete(todoDTO.isComplete());
		
		todoRepository.save(todo);
	}

	@Override
	public void remove(Long tno) {
		todoRepository.deleteById(tno);
	}
}
