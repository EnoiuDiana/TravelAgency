package controller.Admin;

import controller.Common.CellEvents;
import controller.Common.GetObservable;
import controller.Common.SetFXMLComponents;
import controller.Util.DisplayPopup;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.VacationDestination;
import model.VacationPackage;
import service.VacationDestinationService;
import service.VacationPackageService;
import service.Validator.GeneralValidator;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdminVacationDestinationController implements Initializable {
    @FXML
    private TableView<VacationPackage> tableViewVacationPackages;
    @FXML private TableColumn<VacationPackage, String> nameColumn;
    @FXML private TableColumn<VacationPackage, VacationDestination> destinationColumn;
    @FXML private TableColumn<VacationPackage, Integer> priceColumn;
    @FXML private TableColumn<VacationPackage, Date> startDateColumn;
    @FXML private TableColumn<VacationPackage, Integer> periodColumn;
    @FXML private TableColumn<VacationPackage, Integer> limitColumn;
    @FXML private TableColumn<VacationPackage, String> detailsColumn;

    @FXML
    ComboBox<VacationDestination> vacationDestinationComboBox;

    @FXML public TextField vacationDestinationTextField;

    private final VacationPackageService vacationPackageService = new VacationPackageService();
    private final VacationDestinationService vacationDestinationService = new VacationDestinationService();

    VacationDestination vacationDestinationSelectedFromComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetFXMLComponents.setTableViewToDisplayBaseProducts(nameColumn, destinationColumn, priceColumn, startDateColumn,
                periodColumn, limitColumn, detailsColumn);
        tableViewVacationPackages.setEditable(true);
        destinationColumn.setEditable(false);

        SetFXMLComponents.setComboBoxToDisplayDestinations(vacationDestinationComboBox);
        updateComboBox();
    }

    public void updateComboBox() {
        ObservableList<VacationDestination> vacationDestinations = GetObservable.getVacationDestinationObservableList(vacationDestinationService.findAllVacationDestinations());
        try{
            vacationDestinationComboBox.setItems(vacationDestinations);
        } catch(Exception e) {
            DisplayPopup.displayPopup("Error", "No vacation destinations");
        }
    }

    public void comboBoxWasUpdated() {
        vacationDestinationSelectedFromComboBox = vacationDestinationComboBox.getValue();
        List<VacationPackage> vacationPackageList = vacationPackageService.findVacationPackageByDestination(vacationDestinationSelectedFromComboBox);
        tableViewVacationPackages.setItems(GetObservable.getVacationPackagesObservableList(vacationPackageList));
    }

    public void changeCellEvent(TableColumn.CellEditEvent editedCell) {
        VacationPackage vacationPackageSelected = tableViewVacationPackages.getSelectionModel().getSelectedItem();
        CellEvents.changeCellEvent(editedCell, vacationPackageSelected, vacationPackageService);
    }

    public void addVacationDestinationButtonPushed(ActionEvent event) {
        String vacationDestinationName = vacationDestinationTextField.getText();
        try {
            VacationDestination vacationDestination = new VacationDestination(GeneralValidator.stringValidate(vacationDestinationName));
            vacationDestinationService.createNewVacationDestination(vacationDestination);
            updateComboBox();
            vacationDestinationTextField.clear();
        } catch (Exception e) {
            DisplayPopup.displayPopup("Error", "Invalid destination name");
        }
    }


    public void deleteVacationDestinationButtonPushed(ActionEvent event) {
        vacationDestinationService.deleteVacationDestinationById(vacationDestinationSelectedFromComboBox.getId());
        tableViewVacationPackages.setItems(null);
        updateComboBox();
    }
}
