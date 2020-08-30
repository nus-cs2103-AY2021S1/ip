package duke.controller;

import duke.Duke;
import duke.control.DialogueBox;
import duke.core.Result;
import duke.handle.LoadingException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

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

    private Stage stage;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Starts the bot and loads the local record with
     * the load message and greeting message shown.
     *
     * @param stage The stage to show the information.
     */
    public void start(Stage stage) {
        try {
            duke = new Duke();
            dialogContainer.getChildren().addAll(
                    DialogueBox.getDukeDialogueBox(duke.getUi().showLoad(), dukeImage)
            );
            this.stage = stage;
        } catch (FileNotFoundException fileNotFoundException) {
            dialogContainer.getChildren().addAll(
                    DialogueBox.getDukeDialogueBox(fileNotFoundException.getMessage(), dukeImage)
            );
        } catch (LoadingException loadingException) {
            dialogContainer.getChildren().addAll(
                    DialogueBox.getDukeDialogueBox(loadingException.getMessage(), dukeImage)
            );
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogueBox.getDukeDialogueBox(duke.getUi().showGreeting(), dukeImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Result response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogueBox.getUserDialogueBox(input, userImage),
                DialogueBox.getDukeDialogueBox((String) response.getMessage(), dukeImage)
        );
        userInput.clear();
        if (!(response.isContinuing())) {

            //Stage stage = (Stage) this.getScene().getWindow();
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
            pauseTransition.setOnFinished((event) -> {
                stage.close();
            });
            pauseTransition.play();
        }
    }
}
