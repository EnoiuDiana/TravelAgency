package repository;

import model.User;
import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserRepo extends JpaDaoImpl<User> {

    public User findByEmail(final String email) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User user;
        try {
            user = (User) em.createQuery("select u from User u where u.email= :email")
                    .setParameter("email", email)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            user = null;
        }

        em.getTransaction().commit();
        em.close();

        return user;
    }
}
