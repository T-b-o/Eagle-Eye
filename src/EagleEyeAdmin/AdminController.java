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
import javafx.stage.StageStyle;

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
    void lnkSignUpEvent(ActionEvent event){
        try {
            new RegisterController().openRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void loadLogin() throws IOException{
        Parent mainScreen = FXMLLoader.load(this.getClass().getResource("/EagleEyeAdmin/login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(mainScreen));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
