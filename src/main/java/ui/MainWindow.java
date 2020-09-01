package ui;

import command.CommandHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    @FXML
    private TextField input;

    @FXML
    private TextArea output;

    @FXML
    public void initialize() {
        output("Hello from iPbot, what can I do for you?");
    }

    @FXML
    private void handleUserInput(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // get the input
            String cmdString = input.getText();
            input.clear();

            if (!cmdString.isBlank()) {
                // echo the input to output
                echoInput(cmdString);

                // send the input to iPbot
                String output = new CommandHandler().apply(cmdString);
                output(output);
            }
        }
    }

    private void echoInput(String inputString) {
        output.appendText(inputString + "\n\n");
    }

    private void output(String outputString) {
        output.appendText(HORIZONTAL_LINE + "\n");
        output.appendText(outputString + "\n");
        output.appendText(HORIZONTAL_LINE + "\n");
    }

}