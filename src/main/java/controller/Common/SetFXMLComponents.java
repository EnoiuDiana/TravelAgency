package controller.Common;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.VacationDestination;
import model.VacationPackage;

import java.util.Date;

public class SetFXMLComponents {
    public static void setTableViewToDisplayBaseProducts(TableColumn<VacationPackage, String> nameColumn,
                                                         TableColumn<VacationPackage, VacationDestination> destinationColumn,
                                                         TableColumn<VacationPackage, Integer> priceColumn,
                                                         TableColumn<VacationPackage, Date> startDateColumn,
                                                         TableColumn<VacationPackage, Integer> periodColumn,
                                                         TableColumn<VacationPackage, Integer> limitColumn,
                                                         TableColumn<VacationPackage, String> detailsColumn) {

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        destinationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<VacationDestination>() {
            @Override
            public String toString(VacationDestination object) {
                return object.toString();
            }

            @Override
            public VacationDestination fromString(String string) {
                return null;
            }
        }));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        periodColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        limitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        detailsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("vacationDestination"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("period"));
        limitColumn.setCellValueFactory(new PropertyValueFactory<>("noOfPeople"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("extraDetails"));

    }

    public static void setComboBoxToDisplayDestinations(ComboBox<VacationDestination> vacationDestinationComboBox) {
        vacationDestinationComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<VacationDestination> call(ListView<VacationDestination> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(VacationDestination item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });
    }

    public static void setListToShowNameForVacationPackages(ListView<VacationPackage> vacationPackagesListView) {
        vacationPackagesListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<>() {
            @Override
            public String toString(VacationPackage object) {
                return object.getName();
            }

            @Override
            public VacationPackage fromString(String string) {
                return null;
            }
        }));
    }
}



