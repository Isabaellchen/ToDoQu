package rocks.isor.todoqu.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.isor.todoqu.model.repository.TodoRepository;

@Component
public class TodoDAO {

    private final TodoRepository repository;

    public TodoDAO(TodoRepository repository) {
        this.repository = repository;
    }


}
