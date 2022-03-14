package controller.Register;

import controller.Util.DisplayPopup;
import controller.Util.NewSceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.User;
import service.UserService;

import java.io.IOException;

public class SignupController {

    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;

    private final UserService userService = new UserService();

    /**
     * If user has an account, then go to login window
     * @param event
     * @throws IOException
     */
    public void loginButtonPushed(ActionEvent event) throws IOException {
        String sceneToLoad = "login";
        NewSceneLoader newSceneLoader = new NewSceneLoader();
        newSceneLoader.loadNewScene(event, sceneToLoad);
    }

    /**
     * When the signup button is pressed a new user is created
     */
    public void signupButtonPushed(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        try{
            User user = userService.validateUser(username, email, password);
            userService.createNewUser(user);
            loginButtonPushed(event);
        } catch (Exception exception) {
            exception.printStackTrace();
            DisplayPopup.displayPopup("Not valid","Email already in use or incorrect data");
        }
    }
}
