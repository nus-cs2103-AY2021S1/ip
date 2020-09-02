package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
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

    /** Image objects initialized */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/standard_user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/standard_robot.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /** Initializes the Duke object
     *
     * @param d  Duke object for initialization of the Duke object for later use
     */
    public void setDuke(Duke d) {
        duke = d;
        DialogBox db = DialogBox.getDukeDialog("Hello! This is J.A.R.V.I.S.\nHow may I help you?", dukeImage);
        dialogContainer.getChildren().addAll(db);
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        for (String r : response) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(r, dukeImage)
            );
        }
        userInput.clear();
    }

}
