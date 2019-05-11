package EagleEyeAdmin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    private AnchorPane anchLoginScreen;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink lnkSignUp;
    @FXML
    private Label lblClose;

    public AdminController() {
    }

    @FXML
    void btnLoginEventHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/EagleEyeAdmin/MainScreen.fxml"));
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(root));
        mainStage.setTitle("Eagle Eye");
        mainStage.show();
        this.anchLoginScreen.getScene().getWindow().hide();
    }

    @FXML
    void lblCloseEventHandler(MouseEvent event) {
        System.exit(0);
    }
}
