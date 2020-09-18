package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for duke.gui.MainWindow. Provides the layout for the other controls.
 * Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">this guide</a>.
 */
public class MainWindow extends AnchorPane {
    public static final int EXIT_DELAY_TIME_IN_MILLISECONDS = 5000;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public MainWindow(Duke duke) {
        this.duke = duke;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(this.duke.start(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Exits if bot stops running.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (!duke.isRunning()) {
            Duration delayDuration = new Duration(EXIT_DELAY_TIME_IN_MILLISECONDS);
            KeyFrame keyFrame = new KeyFrame(delayDuration, x -> Platform.exit());
            Timeline tl = new Timeline(keyFrame);
            tl.play();
        }
    }
}
