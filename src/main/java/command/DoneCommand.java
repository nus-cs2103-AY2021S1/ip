package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

/**
 * A DoneCommand object marks a task in the existing list as done.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-09-01
 */
public class DoneCommand extends Command {

    /**
     * Marks an existing task as done.
     *
     * @param inputMsg User input which contains the task number to be marked done.
     * @param currList Current list of tasks.
     * @param ui Ui object relevant to the chat bot.
     * @return String message indicating task has been marked as done.
     * @throws DukeException If task number indicated does not exist.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui) throws DukeException {
        // gets the done task number
        int taskNumber = Integer.valueOf(inputMsg.split(" ")[1]);
        if (currList.getNumberOfTasks() < taskNumber || taskNumber <= 0) {
            throw new DukeException("There is no such task number!");
        } else {
            Task currTask = currList.get(taskNumber - 1);
            if (currTask.getStatus()) {
                // task has already marked done before
                throw new DukeException("Task has already been completed earlier on!");
            } else {
                return ui.completeTask(currTask);
            }
        }
    }
}
