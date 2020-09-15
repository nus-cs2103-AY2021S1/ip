package duke;

import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.io.FileNotFoundException;

import exception.MissingInfoException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up the Duke logic in the GUI and preload tasks from data.
     * @param d Duke object
     */
    public void setDuke(Duke d) {
        duke = d;

        sendMessage(duke.getUi().greet());

        try {
            duke.getStorage().load(duke.getTaskList());
        } catch (FileNotFoundException e) {
            sendMessage("OOPS!!! Can't access task data.");
        } catch (IOException e) {
            sendMessage("OOPS!!! Something went wrong... Tasks not saved.");
        } catch (MissingInfoException e) {
            sendMessage(e.getMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        duke.getParser().setScanner(new Scanner(userInput.getText()));
        String reply = duke.getParser().executeCommand(duke.getTaskList());
        checkReply(reply);
    }

    /**
     * Prints a message in the chat box as Duke.
     *
     * @param text The message to send.
     */
    private void sendMessage(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    private void addUserAndBotMessage(String reply) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getDukeDialog(reply, dukeImage));
    }

    private void checkReply(String reply) {
        if (reply.equals("bye")) {
            reply = duke.getUi().bye();

            addUserAndBotMessage(reply);
            userInput.clear();

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), e -> {
                duke.getStage().close();
            }));
            timeline.play();
        } else {
            addUserAndBotMessage(reply);
            userInput.clear();
            saveTasks();
        }
    }

    private void saveTasks() {
        try {
            duke.getStorage().save(duke.getTaskList());
        } catch (IOException e) {
            sendMessage("OOPS!!! Something went wrong... Tasks not saved.");
        } catch (MissingInfoException e) {
            sendMessage(e.getMessage());
        }
    }
}
