package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DoneCommand will indicate the Task at the given position is complete.
 *
 * @author Joshua
 */
public class DoneCommand extends Command {
    /**
     * This is the position of the task that is completed.
     */
    private int taskPosition;

    /**
     * Creates a DoneCommand with the position of the completed task in the TaskList.
     *
     * @param position the position of the task in the TaskList.
     */
    public DoneCommand(int position) {
        this.taskPosition = position;
    }

    /**
     * Executes the DoneCommand with the following TaskList, Ui and Storage classes.
     * The completed task in the TaskList at the position will change its status to completed.
     * The Ui will return the output to be displayed to the user. The storage will update
     * with the new TaskList.
     *
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     * @return output to be displayed to the user.
     * @throws DukeException throws exception if the position of the task to be completed is incorrect.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskPosition < taskList.getTaskList().size() && taskPosition >= 0) {
            taskList.getTaskList().get(taskPosition).completeTask();
            storage.updateStorage(taskList);
            output = ui.showDone(taskList.getTaskList().get(taskPosition));
            return output;
        } else {
            throw new DukeException("OOPS !!! Esta tarea aun no existe!");
        }
    }
}
