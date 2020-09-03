package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Main;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Solution below adapted from https://github.com/sc-arecrow/viscount/tree/master
 *
 * @author sc-arecrow
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

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/LukeDaKing.jpg"));
    private Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/LukeDaDuke.jpg"));

    /**
     * Instantiate a new MainWindow component for JavaFx.
     */
    public MainWindow() {
        try {
            duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class
                    .getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dukeWelcomeMessage = duke.getUi().welcomeMessage();
        dialogContainer.getChildren().add(DialogBox
                .getDukeDialog(dukeWelcomeMessage, dukeImage));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("bye")) {
            Platform.exit();
        }

        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } catch (DukeException ex) {
            dialogContainer.getChildren().add(DialogBox
                    .getDukeDialog(ex.toString(), dukeImage));
        }

        userInput.clear();
    }
}