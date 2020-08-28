package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command where user specifies adding deadline task.
 */
public class DeadlineCommand extends ToDoCommand {
    /**
     * Instantiates the DeadlineCommand Object.
     * 
     * @param task Stores Deadline object associated with command.
     */
    public DeadlineCommand(Task task) {
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
