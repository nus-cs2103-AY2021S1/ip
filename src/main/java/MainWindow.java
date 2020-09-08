import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends AnchorPane{

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;


    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/link.gif"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/princess.gif"));

    @FXML
    public void initialize() {
        String welcomeText = "Hey welcome to Zelda's bot!\nThese are the list of commands:\n" +
                "1. list -> to show you the list of tasks\n2. sort -> sort the tasks\n" +
                "3. event/deadline/todo -> add a new task\n4. update -> update an existing task\n" +
                "5. schedule -> to view all tasks for a month/date\n";
        userInput.setPromptText("Enter Commands here!");
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(welcomeText, dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException, IOException {
        //String welcomeText = "Welcome to Duke, How can I help you?";
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        if (input.equals("bye")) {
            Platform.runLater(() -> {
                try {
                    Thread.sleep(3000);
                    Main.endDuke();
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getDukeDialog(e.getMessage(), dukeImage));
                }
            });
        } else {
            userInput.clear();
        }
    }
}
