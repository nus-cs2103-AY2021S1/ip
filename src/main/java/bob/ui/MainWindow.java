package bob.ui;

import java.io.IOException;

import bob.Bob;
import bob.common.Messages;
import bob.exceptions.BobException;
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
    private ImageView imageView;
    private Bob bob;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image bobImage = new Image(this.getClass().getResourceAsStream("/images/Bob.png"));
    private final Image imageTitle = new Image(this.getClass().getResourceAsStream("/images/titleImage.PNG"));



    /**
     * Initializes MainWindow with an introduction message.
     */
    @FXML
    public void initialize() {
        imageView.setImage(imageTitle);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToHeight(false);
        dialogContainer.getChildren().addAll(
                Gap.createGap(),
                DialogBox.getBobDialog(Messages.INTRO, bobImage)
        );
    }

    /**
     * Initializes Bob and show reminders to user on MainWindow.
     */
    public void initializeBob() {
        try {
            this.bob = new Bob();
            dialogContainer.getChildren().addAll(
                    Gap.createGap(),
                    DialogBox.getBobDialog(bob.getReminders(), bobImage));
        } catch (BobException | IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBobDialog(Messages.LOADING_ERROR, bobImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bob's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.nextAction(input);
        dialogContainer.getChildren().addAll(
                Gap.createGap(),
                DialogBox.getUserDialog(input, userImage),
                Gap.createGap(),
                DialogBox.getBobDialog(response, bobImage)
        );
        userInput.clear();
        if (bob.checkExited()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5f));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            userInput.setDisable(true);
            sendButton.setDisable(true);
            pause.play();
        }
    }

    @FXML
    private void closeApplication() {
        Platform.exit();
    }
}
