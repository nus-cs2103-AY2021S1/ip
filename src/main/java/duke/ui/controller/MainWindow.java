package duke.ui.controller;

import duke.Duke;
import duke.DukeException;
import duke.exec.Executable;
import duke.parser.Parser;
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

    // constants
    private static final String EXIT = "bye";

    // instance variables
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        DialogBox greet = DialogBox.getDukeDialog(duke.getUiComponent().greeting(), dukeImage);
        dialogContainer.getChildren().addAll(greet);
    }

    /**
     * Helper method that creates two dialog boxes, one for the user
     * and the other for duke. Clears the user input after
     * processing
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        assert !input.isEmpty() : "Input is empty"; // user input should not be empty
        String output;

        // process user input
        try {
            Executable e = Parser.parse(input);
            output = e.run(duke.getTaskList(), duke.getUiComponent(), duke.getStorage());
        } catch (DukeException e) {
            output = duke.getUiComponent().outputString(e.getMessage());
        }

        // set labels and dialog container, wrap text
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(output, dukeImage)
        );

        userInput.clear();

        // close window upon input "bye"

        //@@ author Lysire-reused
        // Reused from https://github.com/jiayushe/duke/blob/master/src/main/java/duke/MainWindow.java
        if (input.equals(EXIT)) {
            new Thread(() -> {
                try {
                    Thread.sleep(250);
                    System.exit(0);
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().add(
                            DialogBox.getDukeDialog("Exiting!", dukeImage)
                    );
                }
            }).start();
        }
        //@@author
    }
}
