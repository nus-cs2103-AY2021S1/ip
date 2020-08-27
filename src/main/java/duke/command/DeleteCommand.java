package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.exception.InvalidTaskIdException;

/**
 * Represents a delete command
 */
public class DeleteCommand implements Command {

    /**
     * Parses the input to extract the id of task that is to be deleted.
     * Delete the target task.
     * Update the user data
     * Notifies user that it has been executed.
     *
     * @param taskList
     * @param ui
     * @param input
     * @throws InvalidTaskIdException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException {
        int taskId = Parser.parseTaskId(input);

        if (taskId < 0 || taskId >= taskList.taskSize()) {
            throw new InvalidTaskIdException(taskId + 1 + "");
        }

        Task task = taskList.delete(taskId);
        ui.delete(task);
    }
}
