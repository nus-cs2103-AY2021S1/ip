package duke.ui;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(duke.intro(), dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText() + "\t";
        String response = duke.getResponse(input);
        assert response.length() > 0 : "Duke should not be speechless";
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        if (input.trim().equalsIgnoreCase("bye")) {
            duke.returnStage().close();
        }
        userInput.clear();
    }
}
