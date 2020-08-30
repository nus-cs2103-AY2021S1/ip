package taskbot.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import taskbot.logic.Taskbot;


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
    @FXML
    private ImageView title;

    private Taskbot taskbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image taskbotImage = new Image(this.getClass().getResourceAsStream("/images/Taskbot.png"));
    private Image titleImage = new Image(this.getClass().getResourceAsStream("/images/TitleCard.png"));

    /**
     * Initializes the main window to be displayed.
     */
    @FXML
    public void initialize() {
        // Making the scroll bar dynamically update by binding
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Setting the title card to be displayed
        title.setImage(titleImage);

        // Setting up Taskbot to greet the user
        String greeting = "Hello there, my name is TaskBot.\nHow may I be of assistance today?\n";
        dialogContainer.getChildren().add(DialogBox.getTaskbotDialog(greeting, taskbotImage));
    }

    public void setTaskbot(Taskbot taskbot) {
        this.taskbot = taskbot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Check if exit is called
        if (input.equals("bye")) {
            // Pauses for 1.5s for goodbye text to be read before exiting
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5f));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
        String response = taskbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskbotDialog(response, taskbotImage)
        );
        userInput.clear();
    }
}
