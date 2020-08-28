package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command where user specifies adding Event task.
 */
public class EventCommand extends ToDoCommand {
    /**
     * Instantiates the Event Object.
     *
     * @param task Stores Event object associated with command.
     */
    public EventCommand(Task task) {
        super(task);
    }

    /**
     * Adds the task to task list.
     * Renders the Ui for feedback when user adds task successfully.
     * Saves task to storage across sessions.
     *
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui        ui component which user interacts with or sees.
     * @param storage   Object for saving and loading tasks list to hard disk.
     * @throws duke.DukeException if there is error saving task to storage. 
     */
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        super.execute(taskItems, ui, storage);
    }
}
