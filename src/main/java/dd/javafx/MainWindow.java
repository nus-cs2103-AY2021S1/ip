package dd.javafx;

import dd.Duke;
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

    // images from bigheads.io
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/BigheadUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/BigheadDD.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke() {
        duke = new Duke();
        String output = duke.initializeDuke();

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(output, dukeImage)
        );

        printGreeting();
    }

    private void printGreeting() {
        String greeting = duke.sendGreeting();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage)
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
