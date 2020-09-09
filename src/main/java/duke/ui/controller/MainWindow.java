package duke.ui.controller;

import duke.main.Duke;
import duke.tools.Format;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Sets the Duke object to the given duke.
     *
     * @param duke Input Duke object.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.getUi().greet(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Format<String> format = new Format<>(input);
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(format.toString(), userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (duke.getUi().getParser().isEnd(input)) {
            closeWindow();
        }
        userInput.clear();
    }

    /**
     * Closes the interaction window for
     * when the user types in "bye" command.
     */
    private void closeWindow() {
        userInput.setOnAction(null);
        sendButton.setOnAction(null);
    }
}
