package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Doge.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/PepeSad.png"));
    private Image greetImage = new Image(this.getClass().getResourceAsStream("/images/PepeGreet.png"));
    private Image failImage = new Image(this.getClass().getResourceAsStream("/images/PepeSad2.png"));
    /**
     * Initializes dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String style = "-fx-background-color: rgba(40,42,47,1);";
        this.dialogContainer.setStyle(style);
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.setSpacing(20);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getGreeting(), greetImage)
        );

    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (response.startsWith("Error")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeErrorDialog(response.substring(6), failImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
