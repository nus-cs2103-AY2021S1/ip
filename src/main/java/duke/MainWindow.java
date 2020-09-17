package duke;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    public ImageView header;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/patrick.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private Image headerImg = new Image(this.getClass().getResourceAsStream("/images/header.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(Background.EMPTY);
        header.setImage(headerImg);
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
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        scrollPane.setPannable(true);
    }

    @FXML
    public void showWelcome() {
        String welcomeMsg = "I'm ready, I'm ready, I'm ready...\n Oh hey there what can I do for you today?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMsg, dukeImage)
        );
    }
}