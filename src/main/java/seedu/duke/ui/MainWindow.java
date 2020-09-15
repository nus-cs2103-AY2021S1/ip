package seedu.duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.duke.Duke;

//@@author Jeffry Lum
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications
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

    private Image userProfile = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeProfile = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private String introMessage = ("Hey I'm Duke...\n" + "What do you wanna do?\n" + "I ain't got all day.");

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(introMessage, dukeProfile));
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
                DialogBox.getUserDialog(input, userProfile),
                DialogBox.getDukeDialog(response, dukeProfile));
        userInput.clear();
    }
}
//@@author