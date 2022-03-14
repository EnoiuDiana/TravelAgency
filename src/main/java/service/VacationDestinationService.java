package service;

import model.VacationDestination;
import repository.VacationDestinationRepo;

import java.util.List;

public class VacationDestinationService {

    private final VacationDestinationRepo vacationDestinationRepo;

    public VacationDestinationService() {
        vacationDestinationRepo = new VacationDestinationRepo();
        vacationDestinationRepo.setClazz(VacationDestination.class);
    }

    public void createNewVacationDestination(VacationDestination vacationDestination) {
        vacationDestinationRepo.create(vacationDestination);
    }

    public List<VacationDestination> findAllVacationDestinations() {
        return vacationDestinationRepo.findAll();
    }

    public VacationDestination findById(Long id) {
        return vacationDestinationRepo.findOne(id);
    }

    public void deleteVacationDestinationById(final long id) {
        vacationDestinationRepo.deleteById(id);
    }
}
