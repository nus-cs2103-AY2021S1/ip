package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/** The main class of the app. */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private static final String path = "./data/duke.txt";

    /**
     * Constructs a Duke object associated with a file path.
     * @param path The location of the storage file.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage(path);
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.saveErrorMessage("Error");
            this.taskList = new TaskList();
        } catch (IOException e) {
            ui.saveErrorMessage("Error loading disk");
        }
    }

    /**
     * Parses user input and executes the command returned by the parser.
     *
     * @param input The input from the user as a String.
     */
    public void handleUserInput(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            ui.saveErrorMessage("Sorry, I don't understand that!");
        }
    }

    public String getResponse() {
        return ui.getMessage();
    }
}
