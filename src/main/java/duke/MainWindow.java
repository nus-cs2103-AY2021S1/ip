package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Human.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bot.jpg"));

    public MainWindow() {

    }

    /**
     * Initialises the program's GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Hello from\n" + "Duke Melvin" + "\n" + "What can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, dukeImage)
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
        Response response = duke.getResponse(input);

        DialogBox dukeDialog;
        if (response.isErrorMessage()) {
            dukeDialog = DialogBox.getDukeDialog(response.getResponseMessage(), dukeImage, true);
        } else {
            dukeDialog = DialogBox.getDukeDialog(response.getResponseMessage(), dukeImage);
            if (response.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                dukeDialog
        );
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.clear();

    }
}
