package duke.GUI;

import duke.components.*;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
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

    Image USER_PICTURE = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    Image DUKE_PICTURE = new Image(this.getClass().getResourceAsStream("/images/dukeIcon.png"));

    private Duke duke;

    @FXML
    public void initialize() throws FileNotFoundException, DukeException {
        Storage taskStore = new Storage("duke.txt");
        Storage reminderStore = new Storage("reminders.txt");

        TaskList tasks = new TaskList(taskStore.load(), reminderStore.load());
        Ui ui = new Ui();
        Parser parser = new Parser();
        Label dukeText = new Label(ui.returnStartUpText(tasks.getReminderList()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(DUKE_PICTURE))
        );

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, DukeException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(duke.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(USER_PICTURE)),
                DialogBox.getDukeDialog(dukeText, new ImageView(DUKE_PICTURE))
        );
        userInput.clear();
    }

}
