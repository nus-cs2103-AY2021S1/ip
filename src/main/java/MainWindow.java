import dukeclass.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.TimeUnit;

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

    private boolean isNewWindow = true;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
//        if(isNewWindow) {
//            dialogContainer.getChildren().addAll(
//                    DialogBox.getDukeDialog(Ui.welcomeMessage(), dukeImage)
//            );
//            this.isNewWindow = false;
//        }
        boolean isValidInput = false;
        String input = userInput.getText();
        if (!input.isBlank()) {
            isValidInput = true;
        }

        if (isValidInput) {
            if (input.equals("bye")) {
                duke.endDuke();
                dialogContainer.getChildren().addAll(
                        DialogBox.getDukeDialog(Ui.endMessage(), dukeImage)
                );
            } else {
                String response = duke.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeImage)
                );
            }
            userInput.clear();

        }
    }
}
