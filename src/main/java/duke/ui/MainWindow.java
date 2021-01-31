package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainWindow extends BorderPane {
    private Duke duke;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Initializes the main window of the GUI.
     */
    @FXML
    public void initialize() {
        String welcome = Message.getWelcome().toString();
        dialogContainer.getChildren().add(new BotDialogBox(welcome));
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Manages GUI changes when the user submits an input.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        if (!input.trim().equals("")) {
            dialogContainer.getChildren().addAll(getInputBox(input), getResponseBox(input));
        }
        userInput.clear();
    }

    private UserDialogBox getInputBox(String text) {
        return new UserDialogBox(text);
    }

    private BotDialogBox getResponseBox(String text) {
        String response = duke.getResponse(text);
        if (response == null) {
            Platform.exit();
        }
        return new BotDialogBox(response);
    }
}
