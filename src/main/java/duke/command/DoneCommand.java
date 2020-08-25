package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the done command when user wants to mark task as complete from task list.
 */
public class DoneCommand extends Command {
    private String args;


    /**
     * Initializes the DoneCommand Object. 
     * @param args task to mark as complete.
     */
    public DoneCommand(String args) {
        this.args = args;
    }

    /**
     * Sets task status to complete.
     * Renders task complete message upon successful update.
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
            Task task = taskItems.getTask(itemNumber);
            task.markDone();
            ui.doneTaskReply(task);
            storage.saveTaskToMemory(taskItems.getAll());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.task.Task number does not exist");
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
