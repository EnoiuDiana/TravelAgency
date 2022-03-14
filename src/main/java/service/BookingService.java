package service;

import model.Booking;
import model.User;
import model.VacationDestination;
import model.VacationPackage;
import repository.BookingRepo;
import repository.UserRepo;
import service.Validator.BookingValidator;

import java.util.List;

public class BookingService {
    private final BookingRepo bookingRepo;

    public BookingService() {
        bookingRepo = new BookingRepo();
        bookingRepo.setClazz(Booking.class);
    }

    public void createNewBooking(Booking booking){
        bookingRepo.create(booking);
    }

    public List<Booking> selectAllBookingsByUser(User user) {
        return bookingRepo.findByUser(user);
    }

    public List<Booking> selectAllBookingsByVacationPackage(VacationPackage vacationPackage) {
        return bookingRepo.findByVacationPackage(vacationPackage);
    }

    public Booking validateBooking(Booking booking, User user) throws Exception {
        return BookingValidator.validateBooking(booking, user);
    }

}
