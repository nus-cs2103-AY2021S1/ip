package ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * Controller for ui.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));

    /**
     * Initializes MainWindow controllers.
     */
    @FXML
    public void initialize() {
        Ui ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBoxDuke.getDukeDialog(ui.greet(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.
     * Duke's reply and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        String exitString = "Bye! Hope to see you soon\n";
        dialogContainer.getChildren().addAll(
                DialogBoxUser.getUserDialog(input, userImage),
                DialogBoxDuke.getDukeDialog(response, dukeImage)
        );

        if (response.equals(exitString)) {
            Platform.exit();
        }
        userInput.clear();
        userInput.setPromptText("Enter Command");
    }
}
