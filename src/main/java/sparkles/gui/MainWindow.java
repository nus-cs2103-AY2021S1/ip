package sparkles.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sparkles.Sparkles;

/**
 * Controller for sparkles.gui.MainWindow. Provides the layout for the other controls.
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

    private Sparkles sparkles;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image sparklesImage = new Image(this.getClass().getResourceAsStream("/images/Sparkles-transparent.png"));

    /**
     * Initialise dialogContainer in ScrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Setup the Sparkles bot with the input Sparkles
     *
     * @param s Sparkles bot initiated
     */
    public void setSparkles(Sparkles s) {
        sparkles = s;
    }

    /**
     * Set the dialog Box in the container
     *
     * @param dialogBox dialog box to be setup in the container
     */
    public void setDialogContainer(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sparkles.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSparklesDialog(response, sparklesImage)
        );
        userInput.clear();
    }
}
