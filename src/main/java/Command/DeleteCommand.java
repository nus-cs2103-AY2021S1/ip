package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DeleteCommand will remove the task at the given position from the TaskList.
 *
 * @author Joshua
 */
public class DeleteCommand extends Command {
    /**
     * This is the position of the task to be removed.
     */
    private int taskPosition;

    /**
     * Creates the DeleteCommand.
     *
     * @param position the position of the task in the TaskList.
     */
    public DeleteCommand(int position) {
        this.taskPosition = position;
    }

    /**
     * Executes the DeleteCommand with the following TaskList, Ui and Storage classes.
     * The task at the given position will be removed from the TaskList. The Ui will
     * return the output to be displayed to the user. The storage will update with the
     * new TaskList.
     *
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     * @return output to be displayed to the user.
     * @throws DukeException throws exception if there is no task at the given position.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskPosition < taskList.getTaskList().size() && taskPosition >= 0) {
            output = ui.showDeleted(taskList.getTaskList().get(taskPosition));
            taskList.getTaskList().remove(taskPosition);
            storage.updateStorage(taskList);
            return output;
        } else {
            throw new DukeException("OOPS !!! Esta tarea aun no existe!");
        }
    }
}
