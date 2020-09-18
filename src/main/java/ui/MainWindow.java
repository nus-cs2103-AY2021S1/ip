package ui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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
    @FXML
    private Rectangle logoHeader;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User-logo.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke-logo.jpg"));
    private Image logo = new Image(this.getClass().getResourceAsStream("/images/Header-logo.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        logoHeader.setFill(new ImagePattern(logo));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Greetings from Duke, how may I \nbe of assistance to you?", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (response == null) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Bye. Hope to see you again soon!\n", dukeImage)
            );
            userInput.clear();

            //Solution below adapted from https://stackoverflow.com/questions/27334455
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        } else {
            boolean isValid = !response.isBlank();
            if (isValid) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            }
            userInput.clear();
        }
    }
}
