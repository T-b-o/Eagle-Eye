package EagleEyeAdmin;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import EagleEyeDatabase.EagleEyeDbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    /* Derived Data Types */
    private String userName, Password;

    public AdminController() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
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
    void btnLoginEventHandler(ActionEvent event) {
        try {
            login();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void login() throws IOException{
        Connection conn = null;
        setUserName(txtUserName.getText());
        setPassword(txtPassword.getText());

        //SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");

        try {
            conn = EagleEyeDbConnection.getConnection();
            String loginQuery = "select adminUserName, adminPassword from Administrator " +
                    "where adminUserName = ? and adminPassword = ? ";
            /*Statement queryStatement = conn.createStatement();*/
            PreparedStatement prepStatement = conn.prepareStatement(loginQuery);
            prepStatement.setString(1, getUserName());
            prepStatement.setString(2, getPassword());
            ResultSet loginResult = prepStatement.executeQuery();
            if(loginResult.next() && !getUserName().equals("") && !getPassword().equals("")){
                System.out.println("Login successful!!!");

                /* Get the current user login date and time */
                /*String userLogInfo = "insert into Login(adminID, logTime, logDate)" +
                        "values((select Administrator.adminID from Administrator where Administrator.adminUserName = '" + getUserName() + "')'" +
                        sdf.parse(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))) +
                        "', '" + LocalDate.now().format(DateTimeFormatter.ISO_DATE) + ")'";
                conn.prepareStatement(userLogInfo).executeUpdate();*/
                Parent root = FXMLLoader.load(this.getClass().getResource("/EagleEyeAdmin/MainScreen.fxml"));
                Stage mainStage = new Stage();
                mainStage.setScene(new Scene(root));
                mainStage.setTitle("Eagle Eye");
                mainStage.show();
                this.anchLoginScreen.getScene().getWindow().hide();
            }else{
                System.out.println("Invalid username or password\n" + getUserName() + " : " + getPassword());
            }
            prepStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }/* catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

}
