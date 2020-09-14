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

    private static final String PATH = "./data/duke.txt";

    /** Constructs a Duke object associated with a file path. */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage(PATH);
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
            ui.saveErrorMessage(e.getMessage());
        } catch (IOException e) {
            ui.saveErrorMessage("Sorry, something went wrong");
        }
    }

    public String getResponse() {
        return ui.getMessage();
    }

    public boolean isError() {
        boolean isError = ui.getIsError();
        ui.resetIsError();
        return isError;
    }
}
