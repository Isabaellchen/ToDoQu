package rocks.isor.todoqu.model.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;
import rocks.isor.todoqu.model.dto.LifesWork;
import rocks.isor.todoqu.model.dto.Task;

import java.util.UUID;

@Repository
public interface LifesWorkRepository extends GremlinRepository<LifesWork, String> {
}
