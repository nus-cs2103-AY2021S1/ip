package junimo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import junimo.Junimo;
import junimo.ui.DialogBox;

/**
 * Controller for junimo.ui.MainWindow. Provides the layout for the other controls.
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
    
    private Junimo junimo;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Blue-Junimo.png"));
    private Image junimoImage = new Image(this.getClass().getResourceAsStream("/images/Purple-Junimo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Junimo d) {
        junimo = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(junimo.getWelcome(), junimoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, junimoImage)
        );
        userInput.clear();
    }

    /**
     * Generates a response to user input.
     */
    @FXML
    private String getResponse(String input) {
        return junimo.parseInputCommand(input);
    }
}
