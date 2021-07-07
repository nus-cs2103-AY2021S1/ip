package ui;

import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import storage.TaskList;

/**
 * Controller for Ui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private TaskList tl = Storage.load(Storage.FILE_PATH);
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MarioUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/LuigiDuke.png"));

    /**
     * Start up the application with introduction dialog
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UI.addLines("Mama Mia! I'm Luigi \nWhat can I do for you?"), dukeImage)
        );
    }



    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {

        String input = userInput.getText();
        String response = Parser.parseCode(tl, new UI(), input);

        if (response.equals(UI.addLines("Bye. Hope to see you again soon!"))) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(UI.addLines(response), dukeImage)
            );
            userInput.clear();
            TimeUnit.SECONDS.sleep(1);
            Platform.exit();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();

        }


    }
}
