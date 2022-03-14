package controller.Register;

import controller.Util.DisplayPopup;
import controller.Util.NewSceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.User;
import model.UserType;
import service.UserService;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailTextField;
    @FXML private TextField passwordTextField;

    private static User loggedInUser;

    private final UserService userService = new UserService();

    /**
     * If user doesn't have an account, then go to signup window
     * @param event
     * @throws IOException
     */
    public void signupButtonPushed(ActionEvent event) throws IOException {
        String sceneToLoad = "signup";
        NewSceneLoader newSceneLoader = new NewSceneLoader();
        newSceneLoader.loadNewScene(event, sceneToLoad);
    }

    /**
     * Perform the login
     */
    public void loginButtonPushed(ActionEvent event) throws IOException {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        User userFound = userService.verifyIfUserCanLogin(email, password);
        if(userFound != null) {
            setLoggedInUser(userFound);
            NewSceneLoader newSceneLoader = new NewSceneLoader();
            if (userFound.getUserType() == UserType.ADMIN) {
                newSceneLoader.loadNewScene(event, "admin");
            } else {
                newSceneLoader.loadNewScene(event, "customer");
            }
        } else {
            DisplayPopup.displayPopup("Error","Incorrect username or password");
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        LoginController.loggedInUser = loggedInUser;
    }
}
