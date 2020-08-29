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
 * Driver class for Duke.
 * Contains task list, storage, parser and ui.
 *
 * @author YanCheng
 */

public class Duke extends Application {

    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage(taskList);
    public static Parser parser = new Parser();
    public static Ui ui = new Ui(taskList, storage, parser);

    Stage window;
    // output
    TextArea outputTextArea = new TextArea("Hello! I'm Duke. \nWhat can I do for you?");

    @Override
    public void start(Stage stage) throws Exception {
        // stage is window
        window = stage;
        window.setTitle("Duke Chat-bot");

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
        Label commandLabel = new Label("Command");
        GridPane.setConstraints(commandLabel, 0, 10);

        // command input
        TextField commandInput = new TextField();
        commandInput.setPromptText("Enter command");
        GridPane.setConstraints(commandInput, 1, 10);

        // enter button
        Button enterButton = new Button("Enter");
        GridPane.setConstraints(enterButton, 1, 11);
        enterButton.setOnAction(e -> outputTextArea.setText(ui.readCommand(commandInput.getText())));

        grid.getChildren().addAll(outputTextArea, commandLabel, commandInput, enterButton);

        Scene scene = new Scene(grid, 600, 400);
        window.setScene(scene);
        window.show();
    }
}
