package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

public class DoneCommand extends Command {
    /**
     * Marks an existing task as done.
     *
     * @param inputMsg User input which contains the task number to be marked done.
     * @throws DukeException If task number indicated does not exist.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui) throws DukeException {
        // gets the done task number
        int taskNumber = Integer.valueOf(inputMsg.split(" ")[1]);
        if (currList.getNumOfTasks() < taskNumber || taskNumber <= 0) {
            throw new DukeException("There is no such task number!");
        } else {
            Task currTask = currList.get(taskNumber - 1);
            if (currTask.getStatus()) {
                // task has already marked done before
                throw new DukeException("task.Task has already been completed earlier on!");
            } else {
                return ui.completeTask(currTask);
            }
        }
    }
}
