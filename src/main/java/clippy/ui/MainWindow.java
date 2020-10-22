package clippy.ui;

import clippy.Clippy;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.TimerTask;
import java.util.Timer;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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
    
    private static final int EXIT_DELAY_IN_MILLI_SECONDS = 2000;

    private Clippy clippy;

    private final Image IMAGE_USER = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image IMAGE_CLIPPY = new Image(this.getClass().getResourceAsStream("/images/clippy.jpg"));
    

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcome(), IMAGE_CLIPPY)
        );
        
    }

    public void setClippy(Clippy clippy) {
        this.clippy = clippy;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Clippy's reply and then 
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = clippy.getResponse(input);
        assert !response.isBlank() : "Clippy's response is blank";
        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, IMAGE_USER),
                DialogBox.getDukeDialog(response, IMAGE_CLIPPY)
        );
        
        userInput.clear();
        
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () { 
                    System.exit(0); 
                }
            }, EXIT_DELAY_IN_MILLI_SECONDS);
        }
    }
}
