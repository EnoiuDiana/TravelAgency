package controller.Util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewSceneLoader {

    /**
     * method to load a new scene
     * @param event
     * @param sceneToLoad scene name to be loaded
     * @throws IOException
     */
    public void loadNewScene(ActionEvent event, String sceneToLoad) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/" + sceneToLoad + ".fxml"));
        Parent sceneParent = fxmlLoader.load();
        Scene newScene = new Scene(sceneParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    public void openANewWindow(ActionEvent event, String sceneToLoad) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource( "/" + sceneToLoad + ".fxml"));
        Parent sceneParent = fxmlLoader.load();
        Scene newScene = new Scene(sceneParent);
        Stage window = new Stage();
        window.setScene(newScene);
        window.show();
    }
}
