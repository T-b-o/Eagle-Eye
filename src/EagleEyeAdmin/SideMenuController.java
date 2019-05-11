package EagleEyeAdmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class SideMenuController {

    @FXML
    public static VBox vbxSideMenu;

    @FXML
    private Button btnSetings;

    @FXML
    private Button btnWebcam;

    @FXML
    private Button btnDatabase;

    @FXML
    private Button btnLogout;

    private ColorChangeCallback callback;


    public void setCallback(ColorChangeCallback callback){
        this.callback = callback;
    }

    @FXML
    void btnDatabaseEventHandler(ActionEvent event) {
        try {
            Parent sysMan = FXMLLoader.load(getClass().getResource("/EagleEyeDatabase/DBMS.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Data Management System");
            stage.setScene(new Scene(sysMan));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLogoutEventHandler(ActionEvent event) {
        try {
            Parent sysMan = FXMLLoader.load(getClass().getResource("/EagleEyeAdmin/login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(sysMan));
            stage.show();

            MainScreenController.vbxStage.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSetingsEventHandler(ActionEvent event) {

    }

    @FXML
    void btnWebcamEventHandler(ActionEvent event) {

    }
}
