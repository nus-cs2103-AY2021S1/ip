import duke.Duke;
import duke.Ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.swing.*;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        WelcomeDialogBox welcomeBox = new WelcomeDialogBox(Ui.displayWelcome());
        dialogContainer.getChildren().add(welcomeBox);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        UserDialogBox userBox = new UserDialogBox(input);
        boolean isOk = duke.getWillReturnAsValid();

        if (!isOk) {
            ErrorDialogBox errorBox = new ErrorDialogBox(response);
            dialogContainer.getChildren().addAll(
                    userBox,
                    errorBox
            );
        } else {
            DukeDialogBox dukeBox = new DukeDialogBox(response);
            dialogContainer.getChildren().addAll(
                    userBox,
                    dukeBox
            );
        }

        userInput.clear();

        // briefly delays System.exit to show goodbye message.
        if (input.equals("bye")) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            Runnable task = () -> SwingUtilities.invokeLater(() -> System.exit(0));
            executor.schedule(task, 300, TimeUnit.MILLISECONDS);
        }
    }
}