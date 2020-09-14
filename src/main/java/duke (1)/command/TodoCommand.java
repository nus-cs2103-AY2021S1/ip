package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represent a "Todo" command
 * A <code>TodoCommand</code> object that corresponds to a command of an input "event"
 * and contains a description as a String
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructor of the TodoCommand Class
     *
     * @param description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new Task based on the description
     * Adds the task into the taskList object and prints the corresponding messages.
     * Save the task into the datafile.
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        taskList.addTask(task);
        storage.save(task);
        storage.update();
        return ui.printAddTask(taskList);
    }
}
