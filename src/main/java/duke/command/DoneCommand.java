package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;

/**
 * Represents a done command.
 */
public class DoneCommand implements Command {

    /**
     * Parses the input to extract the id of task that is to be deleted.
     * Mark the target task as done.
     * Update the user data
     * Notifies user that it has been executed.
     *
     * @param taskList
     * @param ui
     * @param input
     * @throws InvalidTaskIdException
     * @return task done message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException {
        int taskId = Parser.parseTaskId(input);

        if (taskId < 0 || taskId >= taskList.taskSize()) {
            throw new InvalidTaskIdException(taskId + 1 + "");
        }

        Task task = taskList.markAsDone(taskId);
        return ui.markAsDone(task);
    }
}
