package duke.ui;

import duke.KK;
import duke.exceptions.DukeException;
import duke.utils.Ui;
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

    private KK duke;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Pikachu.png"));

    /**
     * initialize KK and print help message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DukeDialogBox.getDukeDialog(new Ui().welcomeMessge(), dukeImage),
                DukeDialogBox.getDukeDialog(new Ui().helpMessage(), dukeImage)
        );
    }

    public void setDuke(KK d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing KK's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input),
                DukeDialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
