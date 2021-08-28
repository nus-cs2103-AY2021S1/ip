package duke.gui;

import duke.Duke;
import duke.DukeException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/LukeDaKing.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/LukeDaDuke.png"));

    /**
     * Initializes the main window of the gui.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox
                .getDukeDialog(new Duke()
                        .getUi()
                        .welcomeMessage(), dukeImage
                ));
    }

    /**
     * Sets duke object to duke variable.
     *
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Solution below adapted from https://github.com/pennhanlee/ip
     *
     * @author pennhanlee
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = "";
        try {
            response += duke.getResponse(input);
        } catch (DukeException ex) {
            response += ex;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1500), x -> Platform.exit()
            ));
            timeline.play();
        }
    }
}