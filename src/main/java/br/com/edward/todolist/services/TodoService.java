package br.com.edward.todolist.services;

import java.util.List;

import br.com.edward.todolist.entity.Todo;
import br.com.edward.todolist.repository.TodoRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));

        return todoRepository.findAll(sort);
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> update(Long id, Todo todo) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            try {
                throw new BadRequestException("Todo %d não existe! ".formatted(id));
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });

        return list();

    }

    public List<Todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> todoRepository.delete(existingTodo), () -> {
            try {
                throw new BadRequestException("Todo %d não existe! ".formatted(id));
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        return list();
    }
}