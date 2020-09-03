package dobby.gui;

import dobby.Dobby;
import dobby.Ui;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

    private Dobby dobby;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dobbyImage = new Image(this.getClass().getResourceAsStream("/images/DaDobby.jpeg"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.greet(), dobbyImage)
        );
        dialogContainer.setBackground(
                new Background(
                        new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Dobby d) {
        dobby = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dobby.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dobbyImage)
        );
        userInput.clear();
    }
}
