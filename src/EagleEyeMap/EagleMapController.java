package EagleEyeMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;

import java.net.URL;
import java.util.ResourceBundle;

public class EagleMapController implements Initializable {

    @FXML
    private AnchorPane anchWebMap;

    private WebEngine engine;

    @FXML
    private WebView anchEagleMap;

    @FXML
    private Button btnLocation;

    @FXML
    private Button btnResults;

    @FXML
    void btnLocationEvent(ActionEvent event) {
        /*engine.load("https://www.google.com");*/
        /*engine.load(getClass().getResource("/EagleEyeMap/MapLocation.html").toString());*/
        final URL urlEagleMap = getClass().getResource("/EagleEyeMap/MapLocation.html");
        engine.load(urlEagleMap.toString());
        //anchWebMap.getChildren().add(anchEagleMap);

    }

    @FXML
    void btnResultsEvent(ActionEvent event) {
        engine.load("https://www.linkedin.com/in/teboho-alfred-matjele-a34705bb");
    }


    @FXML
    private Button btnGoogle;

    @FXML
    void btnGoogleEvent(ActionEvent event) {
        /*engine.load("https://www.google.com");*/
        engine.load("https://www.google.com");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = anchEagleMap.getEngine();
    }
}
