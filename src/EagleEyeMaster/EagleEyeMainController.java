package EagleEyeMaster;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EagleEyeMainController extends Application {
    static boolean isSplashLoaded = false;

    public EagleEyeMainController() {
    }

    public void start(Stage primaryStage) throws IOException {
        /*Parent root = FXMLLoader.load(this.getClass().getResource("/EagleEyeMaster/EagleEyeSplash.fxml"));*/
        Parent root = FXMLLoader.load(this.getClass().getResource("/EagleEyeDatabase/DBMS.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
