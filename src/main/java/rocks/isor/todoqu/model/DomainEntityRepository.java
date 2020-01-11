package rocks.isor.todoqu.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface DomainEntityRepository<T extends AbstractDomainEntity>
        extends JpaRepository<T, UUID> {

    public Optional<T> findByIdAndDeletedDateIsNull(UUID identifier);

    public Iterable<T> findByDeletedDateIsNull();
}
