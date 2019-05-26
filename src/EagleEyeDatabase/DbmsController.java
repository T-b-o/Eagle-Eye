package EagleEyeDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DbmsController implements Initializable {
    @FXML
    private TableView<ModelTable> tblRecords;
    @FXML
    private TableColumn<ModelTable, Integer> colID;

    @FXML
    private TableColumn<ModelTable, String> colFirstName;

    @FXML
    private TableColumn<ModelTable, String> colLastName;

    @FXML
    private TableColumn<ModelTable, String> colPassword;

    @FXML
    private TableColumn<ModelTable, String> colUserName;

    @FXML
    private TableColumn<ModelTable, String> colEmail;

    @FXML
    private TableColumn<ModelTable, Integer> colPersonId;




    private ObservableList<ModelTable> obList = FXCollections.observableArrayList();

    public DbmsController() {
    }

    void displayRecords() throws ClassNotFoundException, SQLException{
        Connection con = EagleEyeDbConnection.getConnection();
        if (con.isValid(1)) {
            /*ResultSet res = con.createStatement().executeQuery("select Admin_Id, Admin_UserName, Admin_Password from Administrator");*/
            ResultSet res = con.createStatement().executeQuery("select adminID, adminFirstName, adminLastName, " +
                    "adminEmail, adminUserName, adminPassword, personID from Administrator");
            while(res.next()) {
                /*this.obList.add(new ModelTable(res.getInt("Admin_Id"), res.getString("Admin_UserName"), res.getString("Admin_Password")));*/
                this.obList.add(new ModelTable(res.getInt("adminID"), res.getString("adminFirstName"), res.getString("adminLastName"),
                        res.getString("adminEmail"), res.getString("adminUserName"), res.getString("adminPassword"), res.getInt( "personID")));
            }
        }

        con.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayRecords();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        this.colUserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        this.colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        this.colPersonId.setCellValueFactory(new PropertyValueFactory<>("PersonId"));
        this.tblRecords.setItems(this.obList);
    }
}
