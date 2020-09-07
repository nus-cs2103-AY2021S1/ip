package duke;

import java.io.IOException;

import duke.exception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView imageView;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Lisa.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image logoImage = new Image(this.getClass().getResourceAsStream("/images/Logo.png"));

    /**
     * Initialize Duke Ui
     */
    @FXML
    public void initialize() {
        imageView.setImage(logoImage);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showIntro(), dukeImage)
        );
    }

    public void setDuke() {
        try {
            this.duke = new Duke();
        } catch (DukeException | IOException e) {
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("Could not load save file, new task list create.", dukeImage)
            );
        }
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
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (duke.isExited()) {
            Platform.exit();
        }
    }
}
