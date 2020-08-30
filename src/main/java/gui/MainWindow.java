package gui;

import commands.Command;
import duke.Duke;
import duke.Ui;
import exceptions.DukeException;
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
    private static final String USER_IMAGE_PATH = "/images/angry.png";
    private static final String DUKE_IMAGE_PATH = "/images/happy.png";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_PATH));

    /**
     * Initialise the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        printIntro();
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
        try {
            Command command = duke.getParser().parse(input);
            command.exec(duke.getTaskList(), duke.getUi(), duke.getStorage());
        } catch (DukeException e) {
            duke.getUi().setMessageException(e);
        }
        String response = duke.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }


    private void printIntro() {
        DialogBox intro = DialogBox.getDukeDialog(Ui.getIntro(),
                new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_PATH)));
        dialogContainer.getChildren().addAll(intro);
    }
}
