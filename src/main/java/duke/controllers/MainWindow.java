package duke.controllers;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.ui.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for duke.controllers.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final int EXIT_TIMEOUT = 1000;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/coffee1.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/coffee2.jpg"));

    /**
     * Constructs a MainWindow and sets it as the controller of MainWindow.fxml.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the styling properties of the GUI and binds the necessary event handlers.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hi, I'm Duke! How can I help you today? :-)", dukeImage)
        );
        userInput.setOnAction((event) -> handleUserInput());
        sendButton.setOnAction((event -> handleUserInput()));

    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = response.isError()
                ? DialogBox.getErrorDialog(response.getMessage(), dukeImage)
                : DialogBox.getDukeDialog(response.getMessage(), dukeImage);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );

        userInput.clear();
        if (response.shouldExit()) {
            userInput.setDisable(true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, MainWindow.EXIT_TIMEOUT);
        }
    }
}
