import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class MainWindow<List> extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Jigglypuff.jpg"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Pikachu.png"));

    private final Runnable handleExit = () -> {
        assert !userInput.isDisable() : "User input should not be disabled when calling handleExit";
        assert !sendButton.isDisable() : "Button to send message should not be disabled when calling handleExit";

        userInput.setDisable(true);
        sendButton.setDisable(true);
    };

    private final Map<String, Runnable> runnables = Stream.of(
            new AbstractMap.SimpleEntry<>("handleExit", this.handleExit))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input, this.runnables);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
