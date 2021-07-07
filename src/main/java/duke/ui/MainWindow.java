package duke.ui;

import static java.lang.Thread.sleep;

import duke.Duke;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/spiderman.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/matrix.png"));

    /**
     * Initializes main window.
     */
    @FXML
    public void initialize() {
        dialogContainer.setStyle("-fx-background-color: #436060");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(d.getGreeting(), dukeImage)
        );
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();


        //check to exit program
        if (duke.getExitStatus()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(1700);
                    } catch (InterruptedException e) {
                        Platform.exit();
                    }
                    Platform.exit();
                }
            });
        }
    }
}
