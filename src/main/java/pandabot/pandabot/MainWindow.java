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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private final Image pandaBotImage = new Image(this.getClass().getResourceAsStream("/images/panda notetaker.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPandaBot(PandaBot pandaBot) {
        this.pandaBot = pandaBot;
        // show greetings using a dialog box
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(pandaBot.displayWelcome(), pandaBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pandaBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, pandaBotImage)
        );
        userInput.clear();

        if (pandaBot.canExitProgram()) {
            PauseTransition delay = new PauseTransition((Duration.seconds(EXIT_DELAY)));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

}
