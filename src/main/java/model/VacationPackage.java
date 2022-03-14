package model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="vacation_package")
public class VacationPackage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name ="FK_VacationDestinationId", nullable = false)
    private VacationDestination vacationDestination;

    @OneToMany(mappedBy="vacationPackage", cascade = CascadeType.ALL)
    Set<Booking> bookings = new HashSet<>();

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private int period; //in days

    @Column()
    private String extraDetails;

    @Column(nullable = false)
    private int noOfPeople;

    @Column()
    private Status status;

    public VacationPackage(String name, VacationDestination vacationDestination, int price, Date startDate, int period, String extraDetails, int noOfPeople) {
        this.name = name;
        this.vacationDestination = vacationDestination;
        this.price = price;
        this.startDate = startDate;
        this.period = period;
        this.extraDetails = extraDetails;
        this.noOfPeople = noOfPeople;
        this.status = Status.NOT_BOOKED;
    }

    public VacationPackage() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VacationDestination getVacationDestination() {
        return vacationDestination;
    }

    public int getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getPeriod() {
        return period;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVacationDestination(VacationDestination vacationDestination) {
        this.vacationDestination = vacationDestination;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
