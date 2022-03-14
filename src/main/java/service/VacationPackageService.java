package service;

import controller.Util.DisplayPopup;
import model.Booking;
import model.Status;
import model.VacationDestination;
import model.VacationPackage;
import repository.VacationDestinationRepo;
import repository.VacationPackageRepo;
import service.Validator.VacationPackageValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VacationPackageService {

    private final VacationPackageRepo vacationPackageRepo;

    public VacationPackageService () {
        vacationPackageRepo = new VacationPackageRepo();
        vacationPackageRepo.setClazz(VacationPackage.class);
    }

    public void createNewVacationPackage(VacationPackage vacationPackage) {
        vacationPackageRepo.create(vacationPackage);
    }

    public List<VacationPackage> selectAllVacationPackages() {
        return vacationPackageRepo.findAll();
    }

    public void updateVacationPackage(VacationPackage vacationPackage) {
        vacationPackageRepo.update(vacationPackage);
    }

    public VacationPackage findVacationPackageByName(String name) {
        return vacationPackageRepo.findByName(name);
    }

    public void deleteVacationPackageById(final long id) {
        vacationPackageRepo.deleteById(id);
    }

    public List<VacationPackage> findVacationPackageByDestination(VacationDestination vacationDestination) {
        return vacationPackageRepo.findByDestination(vacationDestination);
    }

    public void updateStatusForAllVacationPackages() {
        List<VacationPackage> vacationPackages = vacationPackageRepo.findAll();
        BookingService bookingService = new BookingService();
        for(VacationPackage vacationPackage : vacationPackages) {
            List<Booking> bookings = bookingService.selectAllBookingsByVacationPackage(vacationPackage);
            if(bookings.size() == 0) {
                vacationPackage.setStatus(Status.NOT_BOOKED);
            } else if(bookings.size() < vacationPackage.getNoOfPeople()) {
                vacationPackage.setStatus(Status.IN_PROGRESS);
            } else if(bookings.size() == vacationPackage.getNoOfPeople()) {
                vacationPackage.setStatus(Status.BOOKED);
            }
            vacationPackageRepo.update(vacationPackage);
        }
    }

    public List<VacationPackage> getAllAvailableVacationPackages() {
        List<VacationPackage> vacationPackages = vacationPackageRepo.findAll();
        List<VacationPackage> availableVacationPackages = new ArrayList<>();
        for(VacationPackage vacationPackage : vacationPackages) {
            if(vacationPackage.getStatus() != Status.BOOKED) {
                availableVacationPackages.add(vacationPackage);
            }
        }
        return availableVacationPackages;
    }

    public VacationPackage validateVacationPackage(String name, VacationDestination vacationDestination, String price,
                                                   String startDate, String period, String limit) throws Exception {
        return VacationPackageValidator.validateVacationPackage(name, vacationDestination, price, startDate, period, limit);
    }

    public List<VacationPackage> searchByCriteria(String destinationCriteria, String priceCriteria, String periodCriteria) throws Exception {
        List<VacationPackage> vacationPackagesThatSatisfyCriteria;
        List<VacationPackage> vacationPackageList = getAllAvailableVacationPackages();
        assert vacationPackageList != null;
        try {
            vacationPackagesThatSatisfyCriteria = new ArrayList<>(vacationPackageList.stream().filter(s ->
                    satisfiesCriteria(destinationCriteria, s, "destination") &&
                            satisfiesCriteria(priceCriteria, s, "price") &&
                            satisfiesCriteria(periodCriteria, s, "period")).collect(Collectors.toUnmodifiableList()));

        } catch (Exception e) {
            throw new Exception("No vacation packages satisfy criteria");
        }
        return vacationPackagesThatSatisfyCriteria;
    }

    private boolean satisfiesCriteria(String criteria, VacationPackage vacationPackage, String criteriaType) {
        if(!criteria.equals("")) {
            switch (criteriaType) {
                case "destination" -> {
                    if(vacationPackage.getVacationDestination().getName().toLowerCase().contains(criteria.toLowerCase())) return true;
                }
                case "price" -> {
                    if(Integer.toString(vacationPackage.getPrice()).equals(criteria)) return true;
                }
                case "period" -> {
                    if(Integer.toString(vacationPackage.getPeriod()).equals(criteria)) return true;
                }
            }
            return false;
        }
        return true;
    }
}
