package focus.ui;

import focus.Focus;
import focus.Main;
import focus.storage.Storage;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/** Controller for MainWindow. Provides the layout for the other controls. */
public class MainWindow extends AnchorPane {
    /** Scroll pane for conservations. */
    @FXML
    private ScrollPane scrollPane;
    /** Dialog container for conversations. */
    @FXML
    private VBox dialogContainer;
    /** User's input. */
    @FXML
    private TextField userInput;
    /** Send button for user. */
    @FXML
    private Button sendButton;
    /** Focus. */
    private Focus focus;
    /** Number of times user has input their name. */
    private int numberOfTimes;
    /** Image path of User. */
    private final String userPath = "/images/koya.png";
    /** Image path of Pocus. */
    private final String pocusPath = "/images/rj.png";
    /** Gets the image for User. */
    private final Image userImage = new Image(this.getClass().getResourceAsStream(userPath));
    /** Gets the image for Pocus. */
    private final Image pocusImage = new Image(this.getClass().getResourceAsStream(pocusPath));

    /** Initialises the main window. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        if (!Storage.getUserName().equals("name")) { //existing user
            dialogContainer.getChildren().addAll(
                    DialogBox.getPocusDialog(UI.addressExistingUser(), pocusImage)
            );
            numberOfTimes++;
        } else { // new user
            dialogContainer.getChildren().addAll(
                    DialogBox.getPocusDialog(UI.greetUser(), pocusImage)
            );
        }
    }

    /**
     * Sets Focus.
     *
     * @param focus Focus.
     */
    public void setFocus(Focus focus) {
        this.focus = focus;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Pocus' reply,
     * and appends them to the dialog container. Clears the user input after processing.
     * If user inputs "bye", Focus will close automatically after 5 seconds.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (Storage.getUserName().equals("name") && numberOfTimes == 0) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getPocusDialog(UI.addressNewUser(input), pocusImage)
            );
        }
        if (numberOfTimes != 0) {
            assert (numberOfTimes > 0) : "Number of times user typed should be more than 0 here.";
            String response = focus.getResponse(input);
            if (response.startsWith("\tERROR:")) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getErrorDialog(response, pocusImage)
                );
            } else {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getPocusDialog(response, pocusImage)
                );
            }
            if (input.equals("bye")) {
                PauseTransition delay = new PauseTransition(Duration.seconds(5));
                delay.setOnFinished(event -> Main.getStage().close());
                delay.play();
            }
        }
        numberOfTimes++;
        userInput.clear();
    }
}
