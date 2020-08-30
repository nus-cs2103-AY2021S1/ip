package chatterbox.ui;

import java.io.IOException;

import chatterbox.Chatterbox;
import chatterbox.ChatterboxException;
import chatterbox.Ui;
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

    private Chatterbox chatterbox;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image chatterboxImage = new Image(this.getClass().getResourceAsStream("/images/Chatterbox.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChatterbox(Chatterbox c) {
        chatterbox = c;
        try {
            chatterbox.loadTasks();
        } catch (ChatterboxException | IOException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(Ui.getErrorMessage("Unable to load tasks."), chatterboxImage)
            );
        }
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.getWelcomeMessage(), chatterboxImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chatterbox's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        if (input.equals("bye")) {
            System.exit(0);
        }
        String response = chatterbox.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, chatterboxImage)
        );
        userInput.clear();
    }
}

