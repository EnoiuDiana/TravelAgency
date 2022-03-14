package service.Validator;

import model.Booking;
import model.User;
import service.BookingService;

import java.util.List;

public class BookingValidator {

    public static Booking validateBooking(Booking booking, User user) throws Exception {
        BookingService bookingService = new BookingService();
        List<Booking> bookingsForCurrentUser = bookingService.selectAllBookingsByUser(user);
        for(Booking booking1 : bookingsForCurrentUser) {
            if(booking1.getVacationPackage().getId().equals(booking.getVacationPackage().getId())){
                throw new Exception("Booking already made for this vacation package");
            }
        }
        return booking;
    }

}
