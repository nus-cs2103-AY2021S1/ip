package duke.gui;

import duke.Duke;
import duke.Ui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/daUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/daDuke.png"));

    /**
     * Initializes the dialog container and display greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.greet(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (input.toLowerCase().equals("bye")) {
            exitApplication(input, response);
        }
        addDialogBox(input, response);
        userInput.clear();
    }

    /**
     * Exits the application.
     *
     * @param input Input by the user.
     * @param response Response from duke.
     */
    private void exitApplication(String input, String response) {
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0), e -> addDialogBox(input, response));
        KeyFrame exit = new KeyFrame(Duration.seconds(1.5), e -> System.exit(0));
        Timeline timeline = new Timeline(keyFrame, exit);
        Platform.runLater(timeline::play);
    }

    /**
     * Adds Duke and User dialog to container.
     *
     * @param input Input by the user.
     * @param response Response from duke.
     */
    private void addDialogBox(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
    }
}
