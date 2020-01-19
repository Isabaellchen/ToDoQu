package rocks.isor.todoqu.model.dao;

import org.springframework.stereotype.Component;
import rocks.isor.todoqu.model.repository.TaskRepository;

@Component
public class TodoDAO {

    private final TaskRepository repository;

    public TodoDAO(TaskRepository repository) {
        this.repository = repository;
    }


}
