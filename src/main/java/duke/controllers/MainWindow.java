package duke.controllers;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private Stage stage;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser3.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setStyle("-fx-control-inner-background: #000000;");
        userInput.setStyle("-fx-text-inner-color: #BA55D3;");
//        userInput.setStyle("-fx-control-inner-background: #000000;");
//        userInput.setStyle("-fx-prompt-text-color: white;");
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.welcome(), dukeImage)
        );
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (this.duke.isExit()) {
            closeDuke();
        }
        userInput.clear();
    }


    private void closeDuke() {
        // Disable all user interactable.
        userInput.setOnAction(null);
        sendButton.setOnAction(null);

        // Set short delay for displaying of exit message.
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }
}