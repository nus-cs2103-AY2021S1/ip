package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command object where user executes deletion of task from TaskList.
 */
public class DeleteCommand extends Command {
    private String args;

    /**
     * Initializes the DeleteCommand Object. 
     * @param args task to delete.
     */
    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Removes tasks from task list.
     * Renders delete message upon successful deletion.
     * Updates local storage with updated task list.
     *
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui        ui component which user interacts with or sees.
     * @param storage   Object for saving and loading tasks list to hard disk.
     * @throws duke.DukeException if task number specified by user does not exist.
     */
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        try {
            // parse for argument - item number
            int itemNumber = Integer.parseInt(args.split(" ")[1]) - 1;
            Task task = taskItems.removeTask(itemNumber);
            ui.deleteTaskReply(task, taskItems);
            storage.saveTaskToMemory(taskItems.getAll());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Cannot delete task that does not exist");
        }
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
