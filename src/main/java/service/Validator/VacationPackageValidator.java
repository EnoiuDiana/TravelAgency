package service.Validator;

import model.VacationDestination;
import model.VacationPackage;
import repository.VacationPackageRepo;
import service.VacationPackageService;

import java.util.Date;

public class VacationPackageValidator {

    private static boolean isUniqueName(String name) {
        VacationPackageService vacationPackageService = new VacationPackageService();
        VacationPackage vacationPackage = vacationPackageService.findVacationPackageByName(name);
        return vacationPackage==null;
    }

    public static int isValidPrice(String price) throws Exception {
        int priceNr = Integer.parseInt(price);
        if (priceNr>0 && priceNr<10000) return priceNr;
        throw new Exception("Invalid price");
    }

    public static int isValidPeriod(String period) throws Exception {
        int periodNr = Integer.parseInt(period);
        if (periodNr>0 && periodNr<10000) return periodNr;
        throw new Exception("Invalid period");
    }

    public static int isValidLimit(String limit) throws Exception {
        int limitNr = Integer.parseInt(limit);
        if (limitNr>0 && limitNr<10000) return limitNr;
        throw new Exception("Invalid limit");
    }

    public static VacationPackage validateVacationPackage(String name, VacationDestination vacationDestination, String price,
                                                          String startDate, String period, String limit) throws Exception {
        try{
            assert isUniqueName(name);
            Date date = GeneralValidator.validateDate(startDate);
            int priceNr = isValidPrice(price);
            int periodNr = isValidPeriod(period);
            int limitNr = isValidLimit(limit);
            return new VacationPackage(name, vacationDestination, priceNr, date, periodNr, "None", limitNr);
        } catch (Exception exception) {
            throw new Exception("Invalid input fields");
        }
    }

    public static String validateName(String name) throws Exception {
        if(isUniqueName(name)){
            return name;
        }
        throw new Exception("Invalid name");
    }
}
