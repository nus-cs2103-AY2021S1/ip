package ultron.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ultron.UI;
import ultron.Ultron;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image ultronImage = new Image(this.getClass().getResourceAsStream("/images/Ultron.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Ultron ultron = new Ultron();

    /**
     * Initialise the Mainwindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        introMessage();
    }

    private void introMessage() {
        dialogContainer.getChildren().addAll(
            DialogBox.getUltronDialog(UI.getIntro(), ultronImage)
        );
    }

    public void setUltron(Ultron ultron) {
        this.ultron = ultron;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ultron's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ultron.getResponse(input);
        DialogBox user = DialogBox.getUserDialog(input, userImage);
        DialogBox ultron = DialogBox.getUltronDialog(response, ultronImage);
        dialogContainer.getChildren().addAll(user, ultron);
        userInput.clear();
    }
}
