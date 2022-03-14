package controller.Common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.VacationDestination;
import model.VacationPackage;

import java.util.List;

public class GetObservable {

    public static ObservableList<VacationPackage> getVacationPackagesObservableList(List<VacationPackage> vacationPackages) {
        ObservableList<VacationPackage> vacationPackageObservableList = FXCollections.observableArrayList();
        vacationPackageObservableList.addAll(vacationPackages);
        return vacationPackageObservableList;
    }

    public static ObservableList<VacationDestination> getVacationDestinationObservableList(List<VacationDestination> vacationDestinations) {
        ObservableList<VacationDestination> vacationDestinationsObservableList = FXCollections.observableArrayList();
        vacationDestinationsObservableList.addAll(vacationDestinations);
        return vacationDestinationsObservableList;
    }

/*    *//**
     * Transform the list of base products into an observable list
     * @param baseProducts the list of base products
     * @return the observable list obtained
     *//*
    public static ObservableList<BaseProduct> getBaseProductObservableList(List<BaseProduct> baseProducts) {
        ObservableList<BaseProduct> baseProductObservableList = FXCollections.observableArrayList();
        baseProductObservableList.addAll(baseProducts);
        return baseProductObservableList;
    }

    *//**
     * Transform the list of composite products into an observable list
     * @param compositeProducts the list of composite products
     * @return the observable list obtained
     *//*
    public static ObservableList<CompositeProduct> getCompositeProductObservableList(List<CompositeProduct> compositeProducts) {
        ObservableList<CompositeProduct> compositeProductObservableList = FXCollections.observableArrayList();
        compositeProductObservableList.addAll(compositeProducts);
        return compositeProductObservableList;
    }

    *//**
     * Method to get the base products from the composite product's menu item list
     * @param compositeProduct the composite product
     * @return the list of base products
     *//*
    public static ObservableList<BaseProduct> getBaseProductsFromComposite(CompositeProduct compositeProduct) {
        ObservableList<BaseProduct> baseProductsFromComposite = FXCollections.observableArrayList();
        try {
            assert compositeProduct != null;
            for (MenuItem menuItem : compositeProduct.getMenuItemsList()) {
                if (menuItem instanceof BaseProduct) {
                    baseProductsFromComposite.add((BaseProduct) menuItem);
                }
            }
        } catch (Exception e) {
            System.out.println("Composite product is null");
        }
        return baseProductsFromComposite;
    }*/
}
