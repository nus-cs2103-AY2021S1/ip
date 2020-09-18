package dude.ui;

import dude.Dude;
import javafx.application.Platform;
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

    private Dude dude;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/right.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/left.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Dude d) {
        dude = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogWrapper response = dude.getResponse(input);
        if (response.getExitStatus()) {
            Platform.exit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    response.hasError() ? DialogBox.getDukeErrorDialog(response.getMessage(), dukeImage)
                            : DialogBox.getDukeDialog(response.getMessage(), dukeImage)
            );
            userInput.clear();
        }
    }
}
