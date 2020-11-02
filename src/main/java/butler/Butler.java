package butler;

import butler.command.Command;
import butler.exception.ButlerException;
import butler.io.Parser;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;
import butler.task.TaskListManager;

/**
 * Represents the main logic of a butler that manages a list of tasks for the user.
 *
 * Butler maintains a list of tasks across different sessions.
 * Data of the list of tasks are saved in hard disk within <code>filePath</code>
 * relative to the program file location.
 */
public class Butler {

    private final Storage storage;
    private final Ui ui;
    private TaskListManager taskListManager;

    /**
     * Constructs a butler that stores tasks in the <code>filePath</code>.
     *
     * @param filePath Location where tasks is stored.
     */
    public Butler(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskListManager = new TaskListManager(new TaskList(storage.load()));
        } catch (ButlerException e) {
            taskListManager = new TaskListManager(new TaskList());
        }
    }

    /**
     * Gets a response message to the <code>input</code>.
     *
     * @param input User input.
     * @return Response message to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskListManager, ui, storage);
        } catch (ButlerException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
}
