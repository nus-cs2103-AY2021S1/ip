package nite.ui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nite.Nite;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nite nite;

    private final Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/usercat_circle.png"));
    private final Image niteImage = new Image(this.getClass()
            .getResourceAsStream("/images/blackcat_circle.png"));
    private final String meowSoundPath = "/audio/meow2.wav";

    /**
     * Initializes the user interface upon starting application.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null.";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    /**
     * Greets the user upon starting the application.
     */
    public void greet() {
        String greeting = Ui.showWelcome();
        assert !greeting.isEmpty() : "Welcome message should not be empty.";
        assert niteImage != null : "Image should not be empty.";
        dialogContainer.getChildren().addAll(
                DialogBox.getNiteDialog(greeting, niteImage)
        );
        playSound(meowSoundPath);
    }

    public void setNite(Nite nite) {
        assert nite != null : "Nite should not be null.";
        this.nite = nite;
    }

    private void playSound(String soundPath) {
        // Media sound = new Media(new File(soundPath).toURI().toString());
        assert !soundPath.isEmpty() : "Path should not be empty";
        Media sound = new Media(this.getClass().getResource(soundPath).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    System.exit(0);
                }
            }, 1000);
        }
        assert !input.isEmpty() : "Input should not be empty.";
        String response = nite.getResponse(input);
        assert !response.isEmpty() : "Response should not be empty.";
        assert userImage != null : "Image should not be empty.";
        assert niteImage != null : "Image should not be empty.";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNiteDialog(response, niteImage)
        );
        playSound(meowSoundPath);
        userInput.clear();
    }
}
