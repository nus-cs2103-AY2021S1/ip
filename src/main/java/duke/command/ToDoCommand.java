package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the ToDo Command when user adds tasks to the task list.
 */
public class ToDoCommand extends Command {
    /** Stores ToDo associated with ToDoCommand. */
    private Task task;

    /**
     * Initialzes TodoCommand Object.
     * 
     * @param task ToDo associated with Command.
     */
    public ToDoCommand(Task task) {
        this.task = task;
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
        taskItems.addTask(task);
        ui.addTaskReply(task, taskItems);
        storage.saveTaskToMemory(taskItems.getAll());
    }

    /**
     * Returns instruction to Duke class whether to terminate program.
     *
     * @return bool value to not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
