package pandabot.pandabot;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final double EXIT_DELAY = 2.0;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private PandaBot pandaBot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image pandaBotImage = new Image(this.getClass().getResourceAsStream("/images/panda notetaker.png"));

    /**
     * Initializes the GUI window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up PandaBot in the GUI. A welcome message will be displayed.
     *
     * @param pandaBot the PandaBot program to run
     */
    public void setPandaBot(PandaBot pandaBot) {
        this.pandaBot = pandaBot;
        // show greetings using a dialog box
        dialogContainer.getChildren().add(DialogBox.getPandaBotDialog(pandaBot.displayWelcome(), pandaBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing PandaBot's reply and then appends them
     * to the dialog container. Clears the user input after processing. Exits Application when the bye message
     * is received.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pandaBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPandaBotDialog(response, pandaBotImage)
        );
        userInput.clear();

        if (pandaBot.canExitProgram()) {
            PauseTransition delay = new PauseTransition((Duration.seconds(EXIT_DELAY)));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

}
