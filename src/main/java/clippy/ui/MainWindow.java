package clippy.ui;

import clippy.Clippy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for Clippy.MainWindow. Provides the layout for the other controls.
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

    private Clippy clippy;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image clippyImage = new Image(this.getClass().getResourceAsStream("/images/clippy.jpg"));
    private static final int exitDelayInSeconds = 2000;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Clippy \n" +
                        "What can I do for you?\n", clippyImage)
        );
        
    }

    public void setClippy(Clippy clippy) {
        this.clippy = clippy;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Clippy.Clippy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = clippy.getResponse(input);
        assert !response.isBlank() : "Clippy.Clippy response is blank";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, clippyImage)
        );
        userInput.clear();
        
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () { 
                    System.exit(0); 
                }
            }, exitDelayInSeconds);
            
        }
    }
}