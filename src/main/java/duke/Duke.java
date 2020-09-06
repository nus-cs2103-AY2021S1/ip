package duke;

import duke.command.UserCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * @param filePath file path of the file to be loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public String getResponse(String userinput) {
        try {
            UserCommand command = Parser.parse(userinput);
            String s = command.execute(tasks, ui);
            storage.save(tasks);
            return s;
        } catch (DukeException exception) {
            return exception.getMessage();
        }
    }

}



