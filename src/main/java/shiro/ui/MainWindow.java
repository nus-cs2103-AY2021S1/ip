package shiro.ui;

import shiro.Shiro;
import shiro.command.CommandResult;
import shiro.exception.ShiroException;
import shiro.message.Message;
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

    private Shiro shiro;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/Penguinny.png"));
    private final Image SHIRO_IMAGE = new Image(this.getClass().getResourceAsStream("/images/Shiro.png"));

    /**
     * initializes the main window and send the welcome message to user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getShiroDialog(Message.welcomeMessage(), SHIRO_IMAGE));
    }

    public void setShiro(Shiro d) {
        shiro = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Shiro's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        CommandResult result = null;
        try {
            result = shiro.getResponse(userInput.getText());
            response = result.getFeedbackToUser();
        } catch (ShiroException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getShiroDialog(response, SHIRO_IMAGE)
        );
        userInput.clear();

        if (result == null) {
            return;
        }

        if (result.isExit()) {
            System.exit(0);
        }
    }
}


