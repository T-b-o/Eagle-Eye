package EagleEyeAdmin;

import com.gluonhq.charm.glisten.control.TextField;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.DataLine.Info;

public class MainScreenController implements Initializable {
    @FXML
    private JFXDrawer drwLeftDrawer;
    @FXML
    private VBox vbLeftDrawer;
    @FXML
    private Button btnSpeaker;
    @FXML
    private JFXHamburger burgerLeftDrawer;
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private RadioButton radOn;
    @FXML
    private RadioButton radOff;
    @FXML
    private ListView<?> lstRecordings;
    @FXML
    private Button btnMic;
    @FXML
    private TextField txtSpeech;
    @FXML
    private TextArea txtSpeechChat;
    @FXML
    private Label lblAdminText;
    @FXML
    private AnchorPane anchDatailsPane;
    @FXML
    private Label lblVicOrPerpetrator;
    @FXML
    private Label lblCoordinates;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;
    @FXML
    private ImageView imgMapLocation;
    @FXML
    private HBox hbxStatusBar;
    @FXML
    private DatePicker dtpSelectDate;
    @FXML
    private Label lblCurrentTime;
    @FXML
    private Color x42;
    @FXML
    private Font x43;
    @FXML
    private Label lblCurrentLocation;
    @FXML
    private Font x31;
    @FXML
    private Color x41;

    public MainScreenController() {
    }

    @FXML
    void btnMicEventHandler(ActionEvent event) {
        this.recordAndPlay();
    }

    @FXML
    void btnSpeakerEventHandler(ActionEvent event) {
        if (this.radOff.selectedProperty().getValue()) {
            this.radOn.selectedProperty().setValue(true);
            this.radOff.selectedProperty().setValue(false);
            this.btnSpeaker.setText("Off");
        } else {
            this.radOn.selectedProperty().setValue(false);
            this.radOff.selectedProperty().setValue(true);
            this.btnSpeaker.setText("On");
        }

    }

    @FXML
    void burgerLeftEventHandler(MouseEvent event) {
    }

    @FXML
    void imgMapEventHandler(MouseEvent event) {
    }

    @FXML
    void lstRecordEventHandler(MouseEvent event) {
    }

    @FXML
    void txtSpeechEventHandler(InputMethodEvent event) {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.radOff.selectedProperty().setValue(true);
        this.btnSpeaker.setText("Speaker-Off");
        this.getDateTime();
        this.dtpSelectDate.setValue(LocalDate.now());
    }

    private void recordAndPlay() {
        try {
            AudioFormat format = new AudioFormat(Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4, 44100.0F, false);
            Info info = new Info(SourceDataLine.class, format);
            final SourceDataLine sdl = (SourceDataLine)AudioSystem.getLine(info);
            sdl.open();
            info = new Info(TargetDataLine.class, format);
            final TargetDataLine tdl = (TargetDataLine)AudioSystem.getLine(info);
            tdl.open();
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Media not supported");
            }

            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            Thread targetThread = new Thread(() -> {
                   tdl.start();
                    byte[] data = new byte[tdl.getBufferSize() / 5];
                    AudioInputStream micRecord = new AudioInputStream(tdl);
                    File voiceRecord = new File("voice.wav");

                    try {
                        AudioSystem.write(micRecord, Type.WAVE, voiceRecord);
                        if (!MainScreenController.this.lstRecordings.getItems().equals(voiceRecord)) {
                            System.out.println("File '" + voiceRecord.getName() + "' exist");
                        }
                    } catch (IOException var6) {
                        var6.printStackTrace();
                    }

                int readBytes;
                    while(true) {
                        readBytes = tdl.read(data, 0, data.length);
                        out.write(data, 0, readBytes);
                    }
            });
            Thread saveVoiceRecord = new Thread(() -> {});
            Thread sourceThread = new Thread(() -> {
                    sdl.start();
                    while(true) {
                        sdl.write(out.toByteArray(), 0, out.size());
                    }
            });
            targetThread.start();
            System.out.println("Recording.....");
            Thread.sleep(5000L);
            tdl.stop();
            tdl.close();
            System.out.println("Recording finished\n\nStart Playback....");
            sourceThread.start();
            Thread.sleep(5000L);
            sdl.stop();
            sdl.close();
            System.out.println("End voice record");
            if (out.size() > 0) {
                saveVoiceRecord.start();
            }
        } catch (InterruptedException var9) {
            var9.printStackTrace();
        } catch (LineUnavailableException var10) {
            var10.printStackTrace();
        }

    }

    private void getDateTime() {
        LocalTime time = LocalTime.now();
        this.lblCurrentTime.setText(time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
    }

    public void getSelectedDate() {
        this.dtpSelectDate.setValue(this.dtpSelectDate.getValue());
    }
}
