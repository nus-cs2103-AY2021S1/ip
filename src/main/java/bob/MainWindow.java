package bob;

import bob.exception.BobException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /**
     * Enables scrolling in the MainWindow.
     */
    @FXML
    private ScrollPane scrollPane;

    /**
     * A container for all DialogBoxes.
     */
    @FXML
    private VBox dialogContainer;

    /**
     * Text to be displayed in the MainWindow's dialogBox.
     */
    @FXML
    private TextField userInput;

    /**
     * A button that can be interacted with to send messages.
     */
    @FXML
    private Button sendButton;

    /**
     * An instance of Bob.
     */
    private Bob bob;

    /**
     * The user's profile picture.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));

    /**
     * Bob's profile picture.
     */
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/Bob.png"));


    /**
     * Initialises the scroll pane of the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /**
     * Constructs a MainWindow to display user input and Bob's responses.
     */
    public MainWindow() {
        this.bob = new Bob("data/save.txt");
        try {
            //@@author sc-arecrow
            //Adapted from https://github.com/sc-arecrow/ip/blame/master/src/main/java/viscount/gui/MainWindow.java
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(UI.greet(), bobImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        try {
            response = bob.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, bobImage)
            );
        } catch (BobException e) {
            response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialogError(response, bobImage)
            );
        }
        if (response == "Bye! I hope to see you soon! \n[This window will close in 3 seconds.]") {

            Duration delay = Duration.seconds(3);
            PauseTransition transition = new PauseTransition(delay);
            transition.setOnFinished(evt -> Platform.exit());
            transition.play();
        }
        userInput.clear();
    }
}