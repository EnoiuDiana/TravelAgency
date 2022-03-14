package controller.Admin;

import controller.Util.NewSceneLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminController {
    public void backButtonPushed(ActionEvent event) throws IOException {
        NewSceneLoader newSceneLoader = new NewSceneLoader();
        newSceneLoader.loadNewScene(event, "login");
    }
}
