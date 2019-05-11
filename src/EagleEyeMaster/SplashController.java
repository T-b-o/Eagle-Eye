package EagleEyeMaster;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashController implements Initializable {
    @FXML
    private AnchorPane anchWelcomeScreen;
    @FXML
    private ImageView imgWelcomeImage;
    @FXML
    private Text txtWelcome;
    private AnchorPane root;

    public SplashController() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!EagleEyeMainController.isSplashLoaded) {
            this.loadWelcomeScreen();
        }

    }

    private void loadWelcomeScreen() {
        try {
            EagleEyeMainController.isSplashLoaded = true;
            this.root = FXMLLoader.load(this.getClass().getResource("EagleEyeSplash.fxml"));
            this.anchWelcomeScreen.getChildren().setAll(this.root);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5.0D), this.root);
            fadeIn.setFromValue(0.0D);
            fadeIn.setToValue(1.0D);
            fadeIn.setCycleCount(1);
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3.0D), this.root);
            fadeOut.setFromValue(1.0D);
            fadeOut.setToValue(0.0D);
            fadeOut.setCycleCount(1);
            fadeIn.play();
            fadeIn.setOnFinished(e -> fadeOut.play());
            fadeOut.setOnFinished((e) -> {
                try {
                    Parent mainScreen = FXMLLoader.load(this.getClass().getResource("/EagleEyeAdmin/login.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(mainScreen));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    this.anchWelcomeScreen.getScene().getWindow().hide();
                } catch (IOException var4) {
                    var4.printStackTrace();
                }

            });
        } catch (IOException var3) {
            var3.getMessage();
        }

    }
}
