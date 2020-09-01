import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.concurrent.TimeUnit;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nigel.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dogPic.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog("Welcome to Duke!", dukeImage)
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
            try {
                System.out.println("d");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Main.endDuke();
            }
        } else {
            userInput.clear();
        }
    }
}
