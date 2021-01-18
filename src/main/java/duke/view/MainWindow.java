package duke.view;

import duke.Duke;
import duke.commands.ExitCommand;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/img_user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/img_duke.png"));
    private final Image errorImage = new Image(this.getClass().getResourceAsStream("/images/img_error.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        displayDuke(duke.init());
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

        if (response.startsWith("OOPS")) {
            displayError(response);
        } else {
            displayDuke(response);
        }
        if (response.equals(ExitCommand.EXIT_FLAG)) {
            System.exit(0);
        }
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

    private void displayError(String err) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(err, errorImage)
        );
    }
}
