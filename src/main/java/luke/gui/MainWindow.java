package luke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luke.Luke;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
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

    private final double DIALOG_BOX_SPACING = 5.0;

    private Luke luke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userIcon.jpg"));
    private Image lukeImage = new Image(this.getClass().getResourceAsStream("/images/lukeIcon.jpg"));

    /**
     * Initializes initial settings for MainWindow.
     *
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Greets the user with a welcome message.
     *
     * @param luke Luke object
     */
    public void greet(Luke luke) {
        setLuke(luke);
        dialogContainer.getChildren().add(DialogBox.getLukeDialog(this.luke.getUi().showWelcome(), lukeImage));
    }

    public void setLuke(Luke luke) {
        this.luke = luke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the dialog
     * container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLukeDialog(response, lukeImage)
        );
        dialogContainer.setSpacing(DIALOG_BOX_SPACING);
        userInput.clear();
    }
}