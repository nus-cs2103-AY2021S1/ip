package botbot.ui;

import botbot.Botbot;
import botbot.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author wakululuu-reused.
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Botbot botbot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private final Image botbotImage = new Image(this.getClass().getResourceAsStream("/images/botbot.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getBotbotDialog(Ui.greet(), botbotImage));
        assert dialogContainer.getChildren().size() == 1;
    }

    public void setBotbot(Botbot botbot) {
        this.botbot = botbot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Botbot's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "Empty input";
        String response = botbot.getResponse(input);
        assert response != null : "Empty response";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotbotDialog(response, botbotImage)
        );
        assert dialogContainer.getChildren().size() >= 2;
        userInput.clear();
    }
}
