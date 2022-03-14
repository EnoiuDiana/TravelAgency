package service.Validator;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralValidator {

    public static String stringValidate(String str) throws Exception {
        String regex = "^[a-zA-Z]+$";
        if (str.matches(regex)) {
            return str;
        }
        throw new Exception("Does not have letters only");
    }

    public static int validateDay(int day, int month, int year) throws Exception {
        switch(month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                if (day>= 1 && day <= 31) return day;
            }
            case 2 -> {
                if (day >= 1 && day <= 29 && year % 4 == 0 || day >= 1 && day <= 28) return day;
            }
            case 4, 6, 9, 11 -> {
                if (day>= 1 && day <= 30) return day;
            }
        }
        throw new Exception("Day is not valid");
    }

    public static int validateMonth(int month) throws Exception {
        if(month >= 1 && month <= 12) {
            return month;
        }
        throw new Exception("Month is not valid");
    }

    public static int validateYear(int year) throws Exception {
        if(year >= 2022 && year <= 2099) {
            return year;
        }
        throw new Exception("Year is not valid");
    }

    private static Date buildDate(int day, int month, int year) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    public static Date validateDate(String dateString) throws Exception {
        Pattern pattern = Pattern.compile("([0-2][0-9])/([0-1][0-9])/(20[2-9][0-9])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(dateString);
        boolean matchFound = matcher.find();
        if(matchFound) {
            int day = Integer.parseInt(matcher.group(1));
            int monthAsNumber = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));
            return buildDate(GeneralValidator.validateDay(day, monthAsNumber, year),
                    GeneralValidator.validateMonth(monthAsNumber),
                    GeneralValidator.validateYear(year));
        }
        throw new Exception("Date cannot be parsed");
    }
}
