package ui;

import duke.Duke;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Sponge.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello from Bikini Bottom!\n"
            + "I'm Sponge\n"
            + "What can I do for you, Bob?"
    , dukeImage));

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
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }


}
