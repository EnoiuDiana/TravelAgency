package controller.Customer;

import controller.Common.GetObservable;
import controller.Common.SetFXMLComponents;
import controller.Register.LoginController;
import controller.Util.DisplayPopup;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Booking;
import model.VacationDestination;
import model.VacationPackage;
import service.BookingService;
import service.VacationDestinationService;
import service.VacationPackageService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerBookController implements Initializable {

    @FXML
    private TableView<VacationPackage> tableViewVacationPackages;
    @FXML private TableColumn<VacationPackage, String> nameColumn;
    @FXML private TableColumn<VacationPackage, VacationDestination> destinationColumn;
    @FXML private TableColumn<VacationPackage, Integer> priceColumn;
    @FXML private TableColumn<VacationPackage, Date> startDateColumn;
    @FXML private TableColumn<VacationPackage, Integer> periodColumn;
    @FXML private TableColumn<VacationPackage, Integer> limitColumn;
    @FXML private TableColumn<VacationPackage, String> detailsColumn;

    @FXML private TextField destinationTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField periodTextField;

    @FXML private ListView<VacationPackage> bookingsListView;

    @FXML private Label totalPriceLabel;
    private int totalPrice = 0;

    @FXML
    ComboBox<VacationDestination> vacationDestinationComboBox;


    private final VacationPackageService vacationPackageService = new VacationPackageService();
    private final BookingService bookingService = new BookingService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //for tableView
        SetFXMLComponents.setTableViewToDisplayBaseProducts(nameColumn, destinationColumn, priceColumn, startDateColumn,
                periodColumn, limitColumn, detailsColumn);
        tableViewVacationPackages.setEditable(false);

        SetFXMLComponents.setComboBoxToDisplayDestinations(vacationDestinationComboBox);

        SetFXMLComponents.setListToShowNameForVacationPackages(bookingsListView);

        updateComboBox();
    }

    public void updateComboBox() {
        VacationDestinationService vacationDestinationService = new VacationDestinationService();
        ObservableList<VacationDestination> vacationDestinations = GetObservable.getVacationDestinationObservableList(vacationDestinationService.findAllVacationDestinations());
        try{
            vacationDestinationComboBox.setItems(vacationDestinations);
        } catch(Exception e) {
            DisplayPopup.displayPopup("Error", "No vacation destinations");
        }
    }

    public void comboBoxWasUpdated() {
        VacationDestination vacationDestinationSelectedFromComboBox = vacationDestinationComboBox.getValue();
        List<VacationPackage> vacationPackageList = vacationPackageService.findVacationPackageByDestination(vacationDestinationSelectedFromComboBox);
        tableViewVacationPackages.setItems(GetObservable.getVacationPackagesObservableList(vacationPackageList));
    }
    public void showVacationPackagesButtonPushed() {
        showVacationPackages();
    }

    private void showVacationPackages() {
        List<VacationPackage> vacationPackageList = vacationPackageService.getAllAvailableVacationPackages();
        tableViewVacationPackages.setItems(GetObservable.getVacationPackagesObservableList(vacationPackageList));
    }

    public void addVacationPackageToOrder() {
        VacationPackage vacationPackage = tableViewVacationPackages.getSelectionModel().getSelectedItem();
        bookingsListView.getItems().add(vacationPackage);
        totalPrice+= vacationPackage.getPrice();
        totalPriceLabel.setText("Total Price: " + totalPrice);
    }

    public void performBookingButtonPushed() {
        ObservableList<VacationPackage> vacationPackagesObservable = bookingsListView.getItems();
        List<VacationPackage> vacationPackagesToBook= new ArrayList<>(vacationPackagesObservable);

        try {
            for (VacationPackage vacationPackage : vacationPackagesToBook) {
                bookingService.createNewBooking(bookingService.validateBooking(new Booking(LoginController.getLoggedInUser(), vacationPackage), LoginController.getLoggedInUser()));
            }
            vacationPackageService.updateStatusForAllVacationPackages();
            DisplayPopup.displayPopup("Success", "Order was placed successfully");
        } catch (Exception e) {
            DisplayPopup.displayPopup("Error", "No order was made");
        }
    }

    public void removeFromListButtonPushed() {
        VacationPackage vacationPackage = bookingsListView.getSelectionModel().getSelectedItem();
        totalPrice -= vacationPackage.getPrice();
        bookingsListView.getItems().removeAll(vacationPackage);
        totalPriceLabel.setText("Total Price: " + totalPrice);
        System.out.println("is called");
    }

    public void searchByCriteriaButtonPushed() {
        String destination = destinationTextField.getText();
        String price = priceTextField.getText();
        String period = periodTextField.getText();

        try{
            List<VacationPackage> vacationPackages = vacationPackageService.searchByCriteria(destination, price, period);
            if(vacationPackages.isEmpty()) {
                DisplayPopup.displayPopup("Sorry", "No products satisfy the criteria that you entered");
            } else {
                tableViewVacationPackages.setItems(GetObservable.getVacationPackagesObservableList(vacationPackages));
            }

        } catch (Exception exception) {
            DisplayPopup.displayPopup("Sorry", "Could not perform search by criteria");
        }
    }
}
