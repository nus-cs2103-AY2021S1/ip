package butler.gui;

import butler.Butler;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Butler butler;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image butlerImage = new Image(this.getClass().getResourceAsStream("/images/DaButler.png"));

    /**
     * Initialises the scroll pane of the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today?\n";
        dialogContainer.getChildren().addAll(
                DialogBox.getButlerDialog(welcomeMsg, butlerImage)
        );
    }

    public void setButler(Butler b) {
        butler = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Butler's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = butler.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getButlerDialog(response, butlerImage)
        );
        userInput.clear();

        // Closes the application upon an exit command
        if (input.equals("bye")) {
            Stage s = (Stage) userInput.getScene().getWindow();
            PauseTransition p = new PauseTransition(Duration.seconds(2));
            p.setOnFinished(event -> s.close());
            p.play();
        }
    }
}
