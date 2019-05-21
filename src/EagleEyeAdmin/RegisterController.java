package EagleEyeAdmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private AnchorPane anchRegister;

    @FXML
    private TextField btnUserName;

    @FXML
    private TextField btnPassword;

    @FXML
    private TextField btnName;

    @FXML
    private TextField btnSurname;

    @FXML
    private TextField btnEmail;

    @FXML
    private Button btnRegister;

    @FXML
    private ImageView icnBackIcon;

    @FXML
    void bntNameEvent(ActionEvent event) {

    }

    @FXML
    void btnEmailEvent(ActionEvent event) {

    }

    @FXML
    void btnPasswordEvent(ActionEvent event) {

    }

    @FXML
    void btnRegisterEvent(ActionEvent event) {

    }

    @FXML
    void btnSurnameEvent(ActionEvent event) {

    }

    @FXML
    void btnUserNameEvent(ActionEvent event) {

    }

    @FXML
    void icnBackIconEvent(MouseEvent event) {
        try {
            new AdminController().loadLogin();
            //anchRegister.getScene().getWindow().hide();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //openRegister();
    }

    public void openRegister() throws IOException {
        Stage stg = new Stage();
        Parent ld = FXMLLoader.load(getClass().getResource("/EagleEyeAdmin/Register.fxml"));
        //anchRegister.getScene().getWindow().hide();
        stg.setScene(new Scene(ld));
        stg.initStyle(StageStyle.UNDECORATED);
        Rectangle clip = new Rectangle();
        clip.width = 39;
        clip.height = 39;




        stg.show();
    }
}
