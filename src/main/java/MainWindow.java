import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private HBox userInput;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //hide scrollbar
        //scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        String openingMsg = "hey hey im Poco \ntype 'help' to view a list of commands"
                                + "\ntype 'format (cmd name)' to view the correct format \ntype 'bye' to exit";
        Label text = new Label(openingMsg);
        text.setStyle("-fx-text-fill: #D0D0D0; -fx-font-family:\"consolas\"; -fx-font-size:14px; -fx-font-weight:bold;");
        text.setPadding(new Insets(10, 0,10, 10));
        dialogContainer.getChildren().remove(userInput);
        dialogContainer.getChildren().addAll(
                text,
                userInput
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        TextField field = (TextField) userInput.getChildren().get(1);
        String input = field.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().remove(userInput);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input)),
                DialogBox.getDukeDialog(new Label(response)),
                userInput
        );

        if (input.equals("bye")) {
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer("Timer");
            timer.schedule(task, 700L);
        }
        field.clear();
    }
}
