package duck.ui;

import duck.Duck;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Duck duck;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image duckImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuck(Duck d) {
        duck = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuckDialog(response, duckImage)
        );
        userInput.clear();
    }

    /**
     * Sends user input to the bot which will return an array of responses.
     *
     * @param input Input from user.
     * @return Responses concatenated into a single string.
     */
    private String getResponse(String input) {
        String[] responses = this.duck.handleInput(input);
        return concatResponseLines(responses);
    }

    /**
     * Concatenates multiple strings into a single string with newline.
     *
     * @param response Variable number of strings.
     * @return Single string as a result of concatenation.
     */
    private String concatResponseLines(String... response) {
        StringBuilder sb = new StringBuilder();
        for (String s : response) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
