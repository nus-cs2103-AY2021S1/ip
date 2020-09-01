package bob.ui;

import java.io.IOException;

import bob.Bob;
import bob.common.MsgGenerator;
import bob.exceptions.BobException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView imageView;
    private Bob bob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/Bob.png"));
    private Image imageTitle = new Image(this.getClass().getResourceAsStream("/images/titleImage.png"));



    /**
     * Initializes MainWindow with an introduction message.
     */
    @FXML
    public void initialize() {
        imageView.setImage(imageTitle);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(MsgGenerator.generateIntroMessage(), bobImage)
        );
    }

    /**
     * Initializes Bob.
     */
    public void initializeBob() {
        try {
            this.bob = new Bob();
        } catch (BobException | IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBobDialog(MsgGenerator.generateLoadingError(), bobImage)
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage)
        );
        userInput.clear();
        if (bob.checkExited()) {
            closeApplication();
        }
    }

    @FXML
    private void closeApplication() {
        Platform.exit();
    }
}
