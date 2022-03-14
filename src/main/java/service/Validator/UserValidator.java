package service.Validator;

import model.User;
import model.UserType;
import model.VacationPackage;
import service.UserService;
import service.VacationPackageService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static boolean isUniqueEmail(String email) {
        UserService userService = new UserService();
        User user = userService.findUserByEmail(email);
        return user==null;
    }

    private static boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private static boolean validateUsername(String username) {
        return username.length()>6;
    }

    public static User validateUser(String username, String email, String password) throws Exception {
        try {
            assert isUniqueEmail(email);
            assert validateEmail(email);
            assert validatePassword(password);
            assert validateUsername(username);
            return new User(username, email, password, UserType.REGULAR);
        }catch (Exception e) {
            throw new Exception("User data not valid");
        }
    }
}
