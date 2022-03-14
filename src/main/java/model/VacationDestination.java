package model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="vacation_destination")
public class VacationDestination implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy="vacationDestination", cascade = CascadeType.ALL)
    Set<VacationPackage> vacationPackages = new HashSet<VacationPackage>();

    public VacationDestination(String name) {
        this.name = name;
    }

    public VacationDestination(){}

    public String getName() {
        return name;
    }

    public Set<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return name;
    }

/*    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VacationDestination)) {
            return false;
        }
        final VacationDestination otherVacationDest = (VacationDestination) other;
        return this.getName().equals(otherVacationDest.getName()) && this.getVacationPackages().equals(otherVacationDest.getVacationPackages())
                && this.getId().equals(otherVacationDest.getId());
    }*/
}
