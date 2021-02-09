package duke;

import duke.label.DialogBox;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

    //Icons from <https://www.flaticon.com/>
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //Background from <"https://id.pinterest.com/pin/811703532819197126/">
        dialogContainer.setStyle(
                "-fx-background-image: url(/images/backgroud.jpg); " +
                "-fx-background-position: center, center; "
        );

        sendButton.setOnMousePressed(mouseEvent -> sendButton.setStyle(
                sendButton.getStyle() +
                "-fx-background-color: #ff4f67; "
        ));

        sendButton.setOnMouseReleased(mouseEvent -> sendButton.setStyle(
                sendButton.getStyle() +
                "-fx-background-color: #E3888A; "
        ));
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.showWelcomeToGui(), dukeImage)
        );
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if(duke.isExit) {
            new Thread(() -> {
                try {
                    Thread.sleep(1300);
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
