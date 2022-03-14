package controller.Common;

import controller.Util.DisplayPopup;
import javafx.scene.control.TableColumn;
import model.VacationPackage;
import service.VacationPackageService;
import service.Validator.VacationPackageValidator;

import javax.swing.text.TableView;
import java.util.Date;
import java.util.List;

public class CellEvents {
    public static void changeCellEvent(TableColumn.CellEditEvent editedCell, VacationPackage vacationPackageSelected,
                                       VacationPackageService vacationPackageService) {
        String columnName = editedCell.getTableColumn().getText();
        Object newValue = editedCell.getNewValue();

        try {
            switch (columnName) {
                case "Name" -> vacationPackageSelected.setName(VacationPackageValidator.validateName(newValue.toString()));
                case "Price" -> vacationPackageSelected.setPrice(VacationPackageValidator.isValidPrice(newValue.toString()));
                case "Start Date" -> vacationPackageSelected.setStartDate((Date) newValue);
                case "Period" -> vacationPackageSelected.setPeriod(VacationPackageValidator.isValidPeriod(newValue.toString()));
                case "Limit" -> vacationPackageSelected.setNoOfPeople(VacationPackageValidator.isValidLimit(newValue.toString()));
                case "Details" -> vacationPackageSelected.setExtraDetails(newValue.toString());
            }
            vacationPackageService.updateVacationPackage(vacationPackageSelected);
        } catch (Exception e) {
            DisplayPopup.displayPopup("Error", "New data not valid");
        }
    }
}
