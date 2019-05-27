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

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;

import com.googlecode.javacv.CanvasFrame;
import org.opencv.core.Core;
import org.opencv.highgui.*;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import org.opencv.videoio.VideoCapture;


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
        MainScreenController ms = new MainScreenController();
        try {
            Parent sysMan = FXMLLoader.load(getClass().getResource("/EagleEyeAdmin/login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(sysMan));

            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
        MainScreenController.vbxStage.getScene().getWindow().hide();
    }

    @FXML
    void btnSetingsEventHandler(ActionEvent event) {

    }

    @FXML
    void btnWebcamEventHandler(ActionEvent event) {
        Thread webCam = new Thread(() -> {
            BufferedImage img;

            CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);

            opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT, 720);
            opencv_highgui.cvSetCaptureProperty(capture, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH, 1280);

            IplImage grabbedImage;
            CanvasFrame frame = new CanvasFrame("CiTix MyWebcam");

            if (frame.isVisible()) {
                JOptionPane.showMessageDialog(null, "Connected");
                while (frame.isVisible() && (grabbedImage = opencv_highgui.cvQueryFrame(capture)) != null) {
                    frame.showImage(grabbedImage);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Not Connected");
            }
        });
        webCam.start();

    }
}
