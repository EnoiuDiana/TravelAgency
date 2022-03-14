package repository;

import model.VacationDestination;
import model.VacationPackage;
import org.hibernate.criterion.CriteriaQuery;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;


public class VacationPackageRepo extends JpaDaoImpl<VacationPackage> {

    public VacationPackage findByName(final String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        VacationPackage vacationPackage;
        try {
            vacationPackage = (VacationPackage) em.createQuery("select p from VacationPackage p where p.name= :packName")
                    .setParameter("packName", name)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            vacationPackage = null;
        }

        em.getTransaction().commit();
        em.close();

        return vacationPackage;
    }

    @SuppressWarnings("unchecked")
    public List<VacationPackage> findByDestination(final VacationDestination vacationDestination) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<VacationPackage> vacationPackages;
        try {
            vacationPackages = em.createQuery("select p from VacationPackage p where p.vacationDestination= :dest")
                    .setParameter("dest", vacationDestination)
                    .getResultList();
        }
        catch (NoResultException e) {
            vacationPackages = null;
        }

        em.getTransaction().commit();
        em.close();

        return vacationPackages;
    }
}
