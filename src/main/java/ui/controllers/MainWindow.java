/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * */

package ui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import king.King;
import ui.UI;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private King king;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gunter.png"));
    private Image kingImage = new Image(this.getClass().getResourceAsStream("/images/king.jpg"));

    /**
     * initialise the MainWindow controller
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getKingDialog(UI.welcome(), kingImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKing(King king) {
        this.king = king;
    }

    /**
     * Takes the user input and returns a
     * dialog. Then take the input and parse it through King,
     * taking the result and return a king dialog.
     *
     * @see UI
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = king.getResponse(input);
        DialogBox kingDialogBox;
        DialogBox userDialogBox;
        if (response.contains(UI.errorBox(""))) {
            kingDialogBox = DialogBox.getErrorDialog(response, kingImage);
        } else {
           kingDialogBox = DialogBox.getKingDialog(response, kingImage);
        }
        userDialogBox = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().addAll(
                userDialogBox,
                kingDialogBox
        );
        userInput.clear();
        if (response.equals(king.getResponse("bye"))) {
            Platform.exit();
        }
    }
}
