package ui;

import command.CommandHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    @FXML
    private TextField input;

    @FXML
    private ListView<HBox> output;

    @FXML
    private Label help;

    @FXML
    private Label taskList;

    /**
     * Method that is called once upon initialization of the application.
     * Performs some setup of the UI elements.
     */
    @FXML
    public void initialize() {
        help.setText(new CommandHandler().apply("help"));
        updateTaskList();
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
                updateTaskList();
            }
        }
    }

    private void updateTaskList() {
        taskList.setText(new CommandHandler().apply("list"));
    }

    private void echoInput(String inputString) {
        output.getItems().add(wrapElement(new Label(inputString), true));
    }

    /**
     * Wraps a node in an {@code HBox}.
     * @param n the node to be wrapped
     * @param isRightAlign whether or not the box should be right-aligned
     * @return the resulting {@code HBox}
     */
    private HBox wrapElement(Node n, boolean isRightAlign) {
        HBox h = new HBox(n);
        if (isRightAlign) {
            h.setAlignment(Pos.CENTER_RIGHT);
        }
        return h;
    }

    private void output(String... outputStrings) {
        final String outputString = String.join("\n", outputStrings);
        output.getItems().add(wrapElement(new Label(outputString), false));
    }

}
