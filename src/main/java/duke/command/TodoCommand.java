package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represent a "Todo" command
 * A <code>TodoCommand</code> object that corresponds to a command of an input "event"
 * and contains a description as a String
 */
public class TodoCommand extends Command{

    String description;

    /**
     * Constructor of the Todo Class
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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.printAddTask(taskList);
        storage.save(task);
    }
}
