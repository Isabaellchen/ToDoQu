package rocks.isor.todoqu.model;

import de.telekom.oreo.auth.exception.OreoAuthException;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractDomainEntityDAO<T extends AbstractDomainEntity> {

    protected abstract DomainEntityRepository<T> getRepository();

    public T update(@NonNull T updated) throws OreoAuthException {
        getRepository()
                .findByIdAndDeletedDateIsNull(updated.getId())
                .orElseThrow(OreoAuthException::entityNotFound);

        updated.setUpdatedDate(new Date());

        return getRepository().save(updated);
    }

    public T delete(@NonNull T toBeDeleted) {
        toBeDeleted.setDeletedDate(new Date());

        return getRepository().save(toBeDeleted);
    }

    public T find(@NonNull UUID uuid) throws OreoAuthException {
        return getRepository()
                .findByIdAndDeletedDateIsNull(uuid)
                .orElseThrow(OreoAuthException::entityNotFound);
    }

    public Iterable<T> findAll() {
        return getRepository().findByDeletedDateIsNull();
    }
}
