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
    private TableColumn<ModelTable, String> colName;
    @FXML
    private TableColumn<ModelTable, String> colPassword;

    private ObservableList<ModelTable> obList = FXCollections.observableArrayList();

    public DbmsController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con = EagleEyeDbConnection.getConnection();
            if (con.isValid(1)) {
                /*ResultSet res = con.createStatement().executeQuery("select Admin_Id, Admin_UserName, Admin_Password from Administrator");*/
                ResultSet res = con.createStatement().executeQuery("select adminID, adminFirstName, adminLastName from Administrator");
                while(res.next()) {
                    /*this.obList.add(new ModelTable(res.getInt("Admin_Id"), res.getString("Admin_UserName"), res.getString("Admin_Password")));*/
                    this.obList.add(new ModelTable(res.getInt("adminID"), res.getString("adminFirstName"), res.getString("adminLastName")));
                }
            }

            con.close();
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        this.colPassword.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        this.tblRecords.setItems(this.obList);
    }
}
