package controller.Admin;

import controller.Common.CellEvents;
import controller.Util.DisplayPopup;
import controller.Common.GetObservable;
import controller.Common.SetFXMLComponents;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Booking;
import model.Status;
import model.VacationDestination;
import model.VacationPackage;
import service.BookingService;
import service.VacationDestinationService;
import service.VacationPackageService;
import service.Validator.GeneralValidator;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdminVacationPackagesController implements Initializable {
    @FXML
    private TableView<VacationPackage> tableViewVacationPackages;
    @FXML private TableColumn<VacationPackage, String> nameColumn;
    @FXML private TableColumn<VacationPackage, VacationDestination> destinationColumn;
    @FXML private TableColumn<VacationPackage, Integer> priceColumn;
    @FXML private TableColumn<VacationPackage, Date> startDateColumn;
    @FXML private TableColumn<VacationPackage, Integer> periodColumn;
    @FXML private TableColumn<VacationPackage, Integer> limitColumn;
    @FXML private TableColumn<VacationPackage, String> detailsColumn;

    @FXML private TextField nameTextField;
    @FXML private TextField destinationTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField startDateTextField;
    @FXML private TextField periodTextField;
    @FXML private TextField limitTextField;
    @FXML private TextField detailsTextField;

    @FXML ComboBox<VacationDestination> vacationDestinationComboBox;

    private final VacationPackageService vacationPackageService = new VacationPackageService();
    private VacationDestination vacationDestinationSelectedFromComboBox;

    private final BookingService bookingService = new BookingService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //for tableView
        SetFXMLComponents.setTableViewToDisplayBaseProducts(nameColumn, destinationColumn, priceColumn, startDateColumn,
                periodColumn, limitColumn, detailsColumn);
        tableViewVacationPackages.setEditable(true);
        destinationColumn.setEditable(false);

        //for combo box
        SetFXMLComponents.setComboBoxToDisplayDestinations(vacationDestinationComboBox);
        updateComboBox();
    }

    public void showVacationPackagesButtonPushed() {
        showVacationPackages();
    }

    private void showVacationPackages() {
        List<VacationPackage> vacationPackageList = vacationPackageService.selectAllVacationPackages();
        tableViewVacationPackages.setItems(GetObservable.getVacationPackagesObservableList(vacationPackageList));
    }

    public void changeCellEvent(TableColumn.CellEditEvent editedCell) {
        VacationPackage vacationPackageSelected = tableViewVacationPackages.getSelectionModel().getSelectedItem();
        CellEvents.changeCellEvent(editedCell, vacationPackageSelected, vacationPackageService);
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
        vacationDestinationSelectedFromComboBox = vacationDestinationComboBox.getValue();
    }

    public void newVacationPackageButtonPushed() {

        try {
            String name = nameTextField.getText();
            String price = priceTextField.getText();
            String startDate = startDateTextField.getText();
            String period = periodTextField.getText();
            String limit = limitTextField.getText();
            VacationDestination vacationDestination = vacationDestinationSelectedFromComboBox;
            VacationPackage vacationPackage = vacationPackageService.validateVacationPackage(name, vacationDestination, price, startDate, period, limit);
            vacationPackageService.createNewVacationPackage(vacationPackage);
            showVacationPackages();
            nameTextField.clear();
            priceTextField.clear();
            startDateTextField.clear();
            priceTextField.clear();
            periodTextField.clear();
            limitTextField.clear();

        } catch (Exception e) {
            DisplayPopup.displayPopup("Invalid data","Date not valid");
        }
    }


    public void deleteVacationPackageButtonPushed() {

        VacationPackage selectedVacationPackage = tableViewVacationPackages.getSelectionModel().getSelectedItem();
        try{
            vacationPackageService.deleteVacationPackageById(selectedVacationPackage.getId());
            showVacationPackages();
        } catch (Exception e) {
            e.printStackTrace();
            DisplayPopup.displayPopup("Error", "Cannot delete vacation package");
        }
    }

    public void showStatus() {
        VacationPackage vacationPackageSelected = tableViewVacationPackages.getSelectionModel().getSelectedItem();
        DisplayPopup.displayPopup("Status", vacationPackageSelected.getStatus().toString());
    }
}
