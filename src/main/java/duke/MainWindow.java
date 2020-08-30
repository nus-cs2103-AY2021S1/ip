package duke;

import duke.dukehelper.uiparts.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dnh.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    @FXML
    public void initialize(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.init(),
                dukeImage));
        setUpFunctionality();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    private void addDialog() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText,
                userImage), DialogBox.getDukeDialog(dukeText, dukeImage));
        userInput.clear();
    }

    private void setUpFunctionality() {
        sendButton.setOnMouseClicked((event) -> {
            addDialog();
        });

        userInput.setOnAction((event) -> {
            addDialog();
        });
    }

    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input,userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
