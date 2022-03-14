package repository;

import java.util.Collection;
import java.util.List;

public interface JpaDao<T> {

    void setClazz(final Class<T> clazzToSet);

    public T findOne(final long id);

    List<T> findAll();

    public T create(final T entity);

    public T update(final T entity);

    void delete(final T entity);

    public void deleteById(final long entityId);
}
