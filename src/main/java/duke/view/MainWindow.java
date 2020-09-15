package duke.view;

import duke.Duke;
import javafx.fxml.FXML;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "Input text from GUI is null";

        displayUser(input);
        userInput.clear();

        String response = duke.getResponse(input);
        assert response != null : "No response from Duke to GUI";

        displayDuke(response);
    }

    private void displayUser(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(msg, userImage)
        );
    }

    private void displayDuke(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(msg, dukeImage)
        );
    }
}
