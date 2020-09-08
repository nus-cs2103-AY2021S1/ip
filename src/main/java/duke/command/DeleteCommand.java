package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.ExceptionType;

/**
 * This class deletes a specific task in the task list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {
        super();
    }

    /**
     * Deletes task at index given.
     *
     * @param index index of task in task list.
     * @param userTasks list of user tasks.
     * @param storage storage object.
     * @return response after execution of command.
     */
    public String execute(int index, TaskList userTasks, Storage storage) throws DukeException {
        try {
            if (index >= userTasks.getTaskListSize()) {
                throw new DukeException("", ExceptionType.INDEX_OUT_OF_BOUNDS);
            } else {
                Task task = userTasks.getTask(index);
                userTasks.deleteTask(index);
                response = new Ui().taskDeletedMessage(task);
            }
            storage.saveToFile(userTasks.getTaskList());
        } catch (DukeException ex) {
            response = new Ui().errorMessage(ex);
        }
        return getResponse();
    }
}
