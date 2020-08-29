package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Duke class.
 * Driver class for Duke with GUI.
 * Contains task list, storage, parser and ui.
 *
 * @author YanCheng
 */

public class Duke extends Application {

    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage(taskList);
    public static Parser parser = new Parser();
    public static Ui ui = new Ui(taskList, storage, parser);

    private final String HELP_TEXT = "Duke Bot Commands:\n" +
            "list : list out all current tasks\n" +
            "find <keyword> : find all task that corresponds to the keyword\n" +
            "done <task number> : marks the specified task as done\n" +
            "delete <task number> : deletes the specified task\n" +
            "todo <task name> : adds a ToDo task\n" +
            "deadline <task name> /by YYYY-MM-DD : adds a Deadline task\n" +
            "event <task name> /at YYYY-MM-DD TT:TT-TT:TT : adds an Event task\n" +
            "Do note that Date and Time must have the specified format\n";
    private Stage window;
    // output
    private TextArea outputTextArea = new TextArea("Hello! I'm Duke. \nWhat can I do for you?");

    /**
     * Starts Duke GUI
     * @param stage Window that is to be launched
     * @throws Exception If any exception occurs during the process
     */
    @Override
    public void start(Stage stage) throws Exception {
        // stage is window
        window = stage;
        window.setTitle("Duke Chat-Bot");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        outputTextArea.setEditable(false); // make output text area not editable
        GridPane.setConstraints(outputTextArea, 0, 0, 10, 10);

        // read duke.txt file and initialise taskList
        try {
            storage.init();
        } catch (DukeException e) {
            outputTextArea.setText(e.getMessage());
        }

        // command label
        Label commandLabel = new Label("Command: ");
        GridPane.setConstraints(commandLabel, 0, 10);

        // command input
        TextField commandInput = new TextField();
        commandInput.setPromptText("Enter command");
        GridPane.setConstraints(commandInput, 1, 10);

        // enter button
        Button enterButton = new Button("Enter");
        GridPane.setConstraints(enterButton, 3, 10);
        enterButton.setOnAction(e -> outputTextArea.setText(ui.readCommand(commandInput.getText())));

        // help button
        Button helpButton = new Button("Help");
        GridPane.setConstraints(helpButton, 1, 11);
        helpButton.setOnAction(e -> PopUpBox.display("Help menu", HELP_TEXT));

        grid.getChildren().addAll(outputTextArea, commandLabel, commandInput, helpButton, enterButton);

        Scene defaultScene = new Scene(grid, 600, 400);
        window.setScene(defaultScene);
        window.show();
    }
}
