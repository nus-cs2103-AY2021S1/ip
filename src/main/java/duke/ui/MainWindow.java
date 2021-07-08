package src.main.java.duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import src.main.java.duke.storage.StorageFile;

/**
 * Represents the controller MainWindow. Provides the layout for the other controls.
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

    private src.main.java.duke.data.Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Constructor for main window
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(src.main.java.duke.data.Duke d) {
        duke = d;
    }

    /**
     * Shows error message to the user
     */
    public void showErrorMessage(String errorMessage) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(errorMessage, dukeImage)
        );
    }

    /**
     * Shows welcome message to the user
     */
    public void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                src.main.java.duke.ui.DialogBox.getUserDialog("Hello! I'm KirbyBot! \n"
                        + "Type 'help' to start owo.", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws StorageFile.StorageOperationException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                src.main.java.duke.ui.DialogBox.getUserDialog(input, userImage),
                src.main.java.duke.ui.DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
