package duke;

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

    private Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    private Image zoroarkImage = new Image(getClass().getResourceAsStream("/images/zoroark.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set duke.
     *
     * @param d duke.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getZoroarkDialog(Ui.getWelcome(), zoroarkImage));
    }

    /**
     * Get Zoroark's message.
     *
     * @param message Zoroark's message.
     */
    public void getZoroarkMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getZoroarkDialog(message, zoroarkImage));
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
                DialogBox.getZoroarkDialog(response, zoroarkImage)
        );
        userInput.clear();
    }
}
