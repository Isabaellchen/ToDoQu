package rocks.isor.todoqu.model.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;
import rocks.isor.todoqu.model.dto.Todo;

import java.util.UUID;

@Repository
public interface TodoRepository extends GremlinRepository<Todo, String> {
}
