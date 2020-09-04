package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import parsers.Parser;

/**
 * Duke is a personal chatbot cum task manager with the following functionalities:
 *     1. add and remove tasks from the list
 *     2. clear the entire list's tasks
 *     3. mark a task as done
 *     4. search for tasks using a query string
 *     5. display all of the existing tasks in the list
 * this class is the main driver for the chatbot
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    static final String FILE_PATH = "data/tasks.txt";

    /**
     * class constructor
     * @param filePath a string representing the destination file path where the list of tasks is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * returns a string corresponding to the response based on the command given
     * @param command the command given by the user
     * @return the appropriate response to the given command
     */
    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
