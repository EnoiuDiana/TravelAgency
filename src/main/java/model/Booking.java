package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="FK_UserId",unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="FK_VacationPackageId",unique = true, nullable = false)
    private VacationPackage vacationPackage;

    public Booking(User user, VacationPackage vacationPackage) {
        this.user = user;
        this.vacationPackage = vacationPackage;
    }

    public Booking() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public VacationPackage getVacationPackage() {
        return vacationPackage;
    }
}
