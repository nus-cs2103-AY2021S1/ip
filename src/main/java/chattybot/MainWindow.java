package chattybot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author Jeffry Lum
//Guides for SE Student Project: Java FX Tutorial
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

    private ChattyBot chattybot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chattyBotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow.
     *
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Prints relevant welcome messages and initialises bot's data storage and UI engine.
     *
     * @param d
     */
    public void setChattyBot(ChattyBot d) {
        chattybot = d;
        dialogContainer.getChildren().addAll(
                DialogBox.createNewDialogBox(chattybot.saysWelcome(), chattyBotImage),
                DialogBox.createNewDialogBox(chattybot.loadStorage(), chattyBotImage),
                DialogBox.createNewDialogBox(chattybot.loadedStorage(), chattyBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChattyBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chattybot.getResponse(input);
        String formattedInput = Ui.formatUserInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(formattedInput, userImage),
                DialogBox.getChattyBotDialog(response, chattyBotImage)
        );
        userInput.clear();
    }
}
