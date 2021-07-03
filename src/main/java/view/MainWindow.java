package view;

import duke.DialogBox;
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.Ui;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/wuXian.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lanZhan.jpg"));

    /**
     * initiliazing gui window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.guiGreeting(), dukeImage));
    }

    /**
     * setting duke
     * @param d
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * When user types "bye", it disables the input and submit button and waits a second
     * for duke to print the goodbye statement before exiting.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            new Thread(() -> {
                try {
                    userInput.setEditable(false);
                    sendButton.setDisable(true);
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().add(
                            DialogBox.getDukeDialog("Lan Zhan is leaving!", dukeImage)
                    );
                }
            }).start();
        }
    }
}
