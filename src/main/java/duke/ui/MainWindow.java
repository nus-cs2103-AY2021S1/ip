package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
        // welcome message
        String welcome = Message.getWelcome().toString();
        dialogContainer.getChildren().add(new DialogBox(welcome, true));

        // padding between buttons
        Insets insets = new Insets(10, 5, 10, 5);
        BorderPane.setMargin(userInput, insets);
        BorderPane.setMargin(sendButton, insets);

        scrollPane.setFitToWidth(true);
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

    private DialogBox getInputBox(String text) {
        return new DialogBox(text, false);
    }

    private DialogBox getResponseBox(String text) {
        String response = duke.getResponse(text);
        return new DialogBox(response, true);
    }
}
