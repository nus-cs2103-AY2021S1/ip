package duke.gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for duke.gui.MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/prompt.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/opus_normal.png"));
    private Image exImage = new Image(this.getClass().getResourceAsStream("/images/opus_exception.png"));

    private Stage stage;

    /**
     * Initializes the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.duke = new Duke("data/tasks.txt");
        String response = this.duke.getGreeting();
        DialogBox db = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(db);
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null;
        String response = duke.getResponse(input);
        assert response != null;

        boolean isException = response.contains("Ex: ");
        DialogBox db;
        if (isException) {
            db = DialogBox.getExceptionDialog(response, exImage);
        } else {
            db = DialogBox.getDukeDialog(response, dukeImage);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                db
        );

        userInput.clear();
        if (response.contains("Terminating")) {
            this.stage.close();
        }
    }
}
