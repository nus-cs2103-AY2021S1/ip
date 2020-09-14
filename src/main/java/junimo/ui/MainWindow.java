package junimo.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import junimo.Junimo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for junimo.ui.MainWindow. Provides the layout for the other controls.
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

    private Junimo junimo;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Blue-Junimo.png"));
    private final Image junimoImage = new Image(this.getClass().getResourceAsStream("/images/Purple-Junimo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJunimo(Junimo d) {
        junimo = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(junimo.getWelcome(), junimoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, junimoImage)
        );
        userInput.clear();
        assert userInput == null;
        if (input.equals("bye")) {
            waitThenExit();
        }
    }
    
    private void waitThenExit() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                junimo.save();
                Platform.exit();
                System.exit(0);
            }
        }, 1000);
    }

    /**
     * Generates a response to user input.
     */
    @FXML
    private String getResponse(String input) {
        return junimo.parseInputCommand(input);
    }
}
