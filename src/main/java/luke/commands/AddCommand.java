package luke.commands;

import java.time.DateTimeException;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.task.Task;

/**
 * Represents a command to add new tasks.
 */
public class AddCommand extends Command {

    protected Task task;

    /**
     * Creates an AddCommand object to add the given task.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            tasks.add(this.task);
            storage.save(tasks);
            return ui.showAddResult(this.task, tasks.getSize());
        } catch (DateTimeException dateTimeException) {
            throw new LukeException("Please enter a valid date format.");
        }
    }
}
