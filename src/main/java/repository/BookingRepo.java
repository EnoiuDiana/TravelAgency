package repository;

import model.Booking;
import model.User;
import model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class BookingRepo extends JpaDaoImpl<Booking> {

    @SuppressWarnings("unchecked")
    public List<Booking> findByUser(final User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Booking> bookings;
        try {
            bookings = em.createQuery("select b from Booking b where b.user= :userThatBooked")
                    .setParameter("userThatBooked", user)
                    .getResultList();
        }
        catch (NoResultException e) {
            bookings = null;
        }

        em.getTransaction().commit();
        em.close();

        return bookings;
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findByVacationPackage(final VacationPackage vacationPackage) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Booking> bookings;
        try {
            bookings = em.createQuery("select b from Booking b where b.vacationPackage= :bookedVacationPackage")
                    .setParameter("bookedVacationPackage", vacationPackage)
                    .getResultList();
        }
        catch (NoResultException e) {
            bookings = null;
        }

        em.getTransaction().commit();
        em.close();

        return bookings;
    }
}
