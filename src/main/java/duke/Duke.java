package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Duke is the name of this program. It acts as a CLI app that reads and save
 * the user inputs. You can use it to record down tasks and marking the progress
 * of it.
 */
public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Initialize the Duke object
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        storage.retrieve(tasks);
        ui = new Ui();
    }

    /**
     * Return duke's response to user's input.
     *
     * @param input User's input.
     * @return Duke's response to user's input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String reply = c.execute(tasks, ui, storage);
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Return the list of tasks.
     *
     * @return The list of tasks.
     */
    public TaskList retrieveTaskList() {
        return tasks;
    }
}
