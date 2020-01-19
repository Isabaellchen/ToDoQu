package rocks.isor.todoqu.model.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;
import rocks.isor.todoqu.model.dto.Reference;
import rocks.isor.todoqu.model.dto.Task;

import java.util.UUID;

@Repository
public interface ReferenceRepository extends GremlinRepository<Reference, String> {
}
