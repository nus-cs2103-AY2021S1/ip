package duke.gui;

import duke.classes.Duke;
import duke.classes.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that defines the frontend page for the main window.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Logger logger = Logger.getLogger("DUKELOGGER");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        getWelcome();
    }

    /**
     * Outputs the welcome string onto the GUI.
     */
    @FXML
    private void getWelcome() {
        dialogContainer.getChildren()
                .addAll(DialogBox.getDukeDialog(new Ui().welcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        System.out.println(input);
        String response = duke.getResponse(input);
        System.out.println(response);

        assert response != null : "Failed to get response!";
        logger.log(Level.INFO, "checking the bye");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        duke.localSave();
        // terminate program
        if (input.equals("bye")) {
            String goodbye = "\nBye! See you soon!";
            Platform.runLater(() -> {
                logger.log(Level.OFF, "it was a success");
                try {
                    TimeUnit.SECONDS.sleep(4);
                    dialogContainer.getChildren().addAll(
                            DialogBox.getDukeDialog(response, dukeImage)
                    );
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(e.getMessage(), dukeImage)
                    );
                }
                Platform.exit();
                logger.log(Level.OFF, "it was a failure");
            });
        }
    }
}
