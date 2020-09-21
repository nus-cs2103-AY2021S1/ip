package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jiji.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/totoro.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/totoroBg2.png"));

    /**
     * initialize scrollPane with a background image.
     */
    @FXML
    public void initialize() {
        BackgroundImage bgImage = new BackgroundImage(this.bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        dialogContainer.setMaxHeight(Double.POSITIVE_INFINITY);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //scrollPane.setFitToHeight(false);
        Background background = new Background(bgImage);
        dialogContainer.setBackground(background);
    }

    public void setDuke(Duke d) {
        duke = d;
        DialogBox welcomeMessage = DialogBox.getUserDialog(d.welcomeMessage(), dukeImage);
        welcomeMessage.flip();
        dialogContainer.getChildren().addAll(welcomeMessage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() throws IOException, DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
