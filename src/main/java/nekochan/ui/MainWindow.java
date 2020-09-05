package nekochan.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nekochan.NekoChan;
import nekochan.command.Response;
import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.util.Messages;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String USER_IMAGE_LOCATION = "/images/User.png";
    private static final String BOT_IMAGE_LOCATION = "/images/Bot.png";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private NekoChan nekoChan;

    // Images from https://www.pngguru.com
    // License: PNGGuru is an open community for users to share PNG cliparts,
    // all PNG cliparts in PNGGuru are for Non-Commercial Use, no attribution required.

    // Image from https://www.pngguru.com/free-transparent-background-png-clipart-kwdio
    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_LOCATION));
    // Image from https://www.pngguru.com/free-transparent-background-png-clipart-mnsxw
    private Image botImage = new Image(this.getClass().getResourceAsStream(BOT_IMAGE_LOCATION));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void start() {
        try {
            nekoChan = new NekoChan(true);
        } catch (NekoStorageException e) {
            print(e.getMessage());
            nekoChan = new NekoChan(false);
        } finally {
            greet();
        }
    }

    /**
     * Retrieves user input and executes it. Prints a dialog box to echo the user input
     * and another dialog box containing the chat bot response.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        printInput(input);
        try {
            Response response = nekoChan.getResponse(input);
            print(response.getResponseMessage());
            if (response.shouldExit()) {
                // Adapted from https://stackoverflow.com/a/27334614
                PauseTransition pause = new PauseTransition((Duration.seconds(2)));
                pause.setOnFinished((event) -> {
                    Platform.exit();
                });
                pause.play();
            }
        } catch (NekoException e) {
            print(e.getMessage());
        } finally {
            userInput.clear();
        }
    }

    private void printInput(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }

    private void print(String response) {
        dialogContainer.getChildren().add(DialogBox.getNekoDialog(response, botImage));
    }

    private void greet() {
        dialogContainer.getChildren().add(DialogBox.getNekoDialog(Messages.MESSAGE_WELCOME, botImage));
    }
}
