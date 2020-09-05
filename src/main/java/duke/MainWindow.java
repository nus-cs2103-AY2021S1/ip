package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DukeGui dukeGui;

    public void setDukeGui(DukeGui d) {
        dukeGui = d;
    }

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/galaxybrain.jpg"));

    private Font userFont = Font.font("Courier New", 12);
    private Font dukeFont = Font.font("Consolas", 12);

    @FXML
    private void initialize() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        //Label label = new Label(input);
        //label.setFont(Font.font("Courier New", 12));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage, userFont)
        );
        if (dukeGui.handleUserInput(input)) {
            Platform.exit();
        }
        userInput.clear();
    }

    public void handleDukeResponse(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage, dukeFont)
        );
    }

}
