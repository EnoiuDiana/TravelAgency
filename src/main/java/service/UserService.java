package service;

import model.User;
import repository.UserRepo;
import service.Validator.UserValidator;

public class UserService {
    private final UserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo();
        userRepo.setClazz(User.class);
    }

    public void createNewUser(User user){
        userRepo.create(user);
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User verifyIfUserCanLogin(String email, String password) {
        User userFoundByEmail = userRepo.findByEmail(email);
        if(userFoundByEmail != null && userFoundByEmail.getPassword().equals(password)) {
            return userFoundByEmail;
        }
        return null;
    }

    public User validateUser(String username, String email, String password) throws Exception {
        return UserValidator.validateUser(username, email, password);
    }


}
