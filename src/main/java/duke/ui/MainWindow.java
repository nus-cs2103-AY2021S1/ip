package duke.ui;

import duke.Duke;
import duke.result.Result;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke, Result loadResult) {
        this.duke = duke;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(loadResult, dukeImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Result response = duke.getResponse(input);
        String responseMessage = response.isSuccessful()
                ? response.getMessage()
                : "Apologies. " + response.getMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.isExit()) {
            exitDuke();
        }
    }

    private void exitDuke() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.7));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
