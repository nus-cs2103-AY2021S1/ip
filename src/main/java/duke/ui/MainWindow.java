package duke.ui;

import duke.Duke;
import duke.response.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nobita.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/doraemon.png"));

    /**
     * Initialises the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the variable duke.
     *
     * @param d The Duke to set duke to.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
            DialogBox.getDoraemonDialog(Response.intro(), dukeImage)
        );
    }

    @SafeVarargs
    private void addToDialogContainer(Node... nodes) {
        dialogContainer.getChildren().addAll(nodes);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        addToDialogContainer(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDoraemonDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals(Response.printBye())) {
            Platform.runLater(() -> {
                try {
                    //Show the bye message for 1.5seconds before exit.
                    Thread.sleep(1500);
                    Platform.exit();
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(e.getMessage(), dukeImage)
                    );
                }
            });
        }
    }
}
