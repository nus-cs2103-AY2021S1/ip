package duckie.ui;

import duckie.Duckie;
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

    private Duckie duckie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duckieImage = new Image(this.getClass().getResourceAsStream("/images/duckie.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuckie(Duckie d) {
        duckie = d;
    }

    /**
     * Creates two dialog boxes, one is to greet and another one displays the available tasks in the list.
     */
    @FXML
    public void showWelcome() {
        String logo = Ui.getLogo();
        String toShow = "Quack. Duckie is here to remember your tasks!";
        dialogContainer.getChildren().add(DialogBox.getDuckieDialog(logo + toShow, duckieImage));
        dialogContainer.getChildren().add(DialogBox.getDuckieDialog(duckie.getResponse("list"), duckieImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duckie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duckie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuckieDialog(response, duckieImage)
        );
        userInput.clear();
    }
}
