package duke;

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

    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image logoImage = new Image(
            this.getClass().getResourceAsStream("/images/logo.png"));

    /**
     * Initialise the main window
     */
    @FXML
    public void initialize() {
        imageView.setImage(logoImage);
        DialogBox logo = DialogBox.getDukeDialog(Ui.getLogoMsg(), dukeImage);
        DialogBox greeting = DialogBox.getDukeDialog(Ui.getGreetingMsg(), dukeImage);
        dialogContainer.getChildren().addAll(logo, greeting);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends them to
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

        if (input.equals("bye")) {
            Platform.runLater(() -> {
                try {
                    exitPlatform();
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(e.getMessage(), dukeImage));
                }
            });
        }
    }

    private void exitPlatform() throws InterruptedException {
        Thread.sleep(2000);
        duke.save();
        Platform.exit();
    }
}
