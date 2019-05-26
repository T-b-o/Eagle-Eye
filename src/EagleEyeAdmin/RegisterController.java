package EagleEyeAdmin;

import EagleEyeDatabase.EagleEyeDbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private AnchorPane anchRegister;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox cmbxPersonType;

    @FXML
    private Button btnRegister;

    @FXML
    private ImageView icnBackIcon;

    /* Derived Data Types */
    private String userName, password, firstName, lastName, email;
    private int personType, adminId;

    private ObservableList<String> obsList = FXCollections.observableArrayList("Administrator", "Perpetrator", "Victim" );

    /* Constructor */

    public RegisterController(){}

   /* public RegisterController(AnchorPane anchRegister, String userName, String password, String firstName, String lastName, String email, int personType, int adminId) {
        this.anchRegister = anchRegister;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personType = personType;
        this.adminId = adminId;
    }*/
    /* Setter and Getter */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /*


    public TextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(TextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public TextField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(TextField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public TextField getTxtFirstName() {
        return txtFirstName;
    }

    public void setTxtFirstName(TextField txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public TextField getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(TextField txtLastName) {
        this.txtLastName = txtLastName;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public ComboBox getCmbxPersonType() {
        return cmbxPersonType;
    }

    public void setCmbxPersonType(ComboBox cmbxPersonType) {
        this.cmbxPersonType = cmbxPersonType;
    }
*/

    @FXML
    void btnRegisterEvent(ActionEvent event){
        InsertUser();
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
        cmbxPersonType.setItems(obsList);
    }

    public void openRegister() throws IOException {
        /*cmbxPersonType.setSelectionModel(new javax.swing.DefaultComboBoxModel<>(e ->
                new String[] {"Administrator", "Victim", "Perpetrator"}
        ));*/
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

    PreparedStatement queryStmnt;

    void InsertUser(){
        Connection con = null;
        setFirstName(txtFirstName.getText());
        setLastName(txtLastName.getText());
        setEmail(txtEmail.getText());
        setUserName(txtUserName.getText());
        setPassword(txtPassword.getText());
        setPersonType(1 + cmbxPersonType.getSelectionModel().getSelectedIndex());
        /*if(cmbxPersonType.getSelectionModel().getSelectedIndex() == 1){
            setPersonType(3 + cmbxPersonType.getSelectionModel().getSelectedIndex());
        }*/

        try {
            con = EagleEyeDbConnection.getConnection();
            if (con.isValid(1)) {
               String query = "insert into Administrator(adminFirstName, adminLastName," +
                        "adminEmail, adminUserName, adminPassword, personID)" +
                        "values('" + getFirstName() + "', '" + getLastName() + "', '" + getEmail() + "'," +
                        "'" + getUserName() + "', '" + getPassword() + "', '" + getPersonType() + "')";
                queryStmnt = con.prepareStatement(query);

                if(queryStmnt.executeUpdate() == 1){
                    System.out.println("Registration successful!!!");
                }else {
                    System.out.println("Register failed");
                }
                //ResultSet result;

            }
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
