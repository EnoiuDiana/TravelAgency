package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class JpaDaoImpl<T> implements JpaDao<T>{
    private Class<T> clazz;

    protected static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("per");

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        T entity =  em.find(clazz, id);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<T> entities =  em.createQuery("from " + clazz.getName()).getResultList();
        em.getTransaction().commit();
        em.close();
        return entities;
    }

    public T create(final T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    public T update(final T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    public void delete(final T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteById(final long entityId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(clazz, entityId));
        em.getTransaction().commit();
        em.close();
    }
}
