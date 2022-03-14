package controller.Customer;

import controller.Common.GetObservable;
import controller.Common.SetFXMLComponents;
import controller.Register.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Booking;
import model.VacationDestination;
import model.VacationPackage;
import service.BookingService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerAllBookingsController implements Initializable {
    @FXML
    private TableView<VacationPackage> tableViewVacationPackages;
    @FXML private TableColumn<VacationPackage, String> nameColumn;
    @FXML private TableColumn<VacationPackage, VacationDestination> destinationColumn;
    @FXML private TableColumn<VacationPackage, Integer> priceColumn;
    @FXML private TableColumn<VacationPackage, Date> startDateColumn;
    @FXML private TableColumn<VacationPackage, Integer> periodColumn;
    @FXML private TableColumn<VacationPackage, Integer> limitColumn;
    @FXML private TableColumn<VacationPackage, String> detailsColumn;

    private final BookingService bookingService = new BookingService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //for tableView
        SetFXMLComponents.setTableViewToDisplayBaseProducts(nameColumn, destinationColumn, priceColumn, startDateColumn,
                periodColumn, limitColumn, detailsColumn);
        tableViewVacationPackages.setEditable(false);
    }

    public void showBookedVacationPackages() {
        List<Booking> bookingsList = bookingService.selectAllBookingsByUser(LoginController.getLoggedInUser());
        List<VacationPackage> vacationPackages = new ArrayList<>();
        for(Booking booking : bookingsList) {
            vacationPackages.add(booking.getVacationPackage());
        }
        tableViewVacationPackages.setItems(GetObservable.getVacationPackagesObservableList(vacationPackages));
    }
}
