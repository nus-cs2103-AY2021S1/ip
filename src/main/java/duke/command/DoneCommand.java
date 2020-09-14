package duke.command;

import duke.exception.FileUpdateFailException;
import duke.exception.InvalidSimpleCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.TaskAlreadyDoneException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Abstracts the marking of task as done command.
 */
public class DoneCommand extends SimpleCommand {

    private final String input;

    /**
     * Initializes the {@code DoneCommand} object.
     *
     * @param input Input string from user.
     */
    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Executes marking of task as done command.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws InvalidSimpleCommandException If Done command is invalid.
     * @throws InvalidTaskNumberException If task number does not lie within the size of TaskList.
     * @throws TaskAlreadyDoneException If task has already been marked done before.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidSimpleCommandException,
        InvalidTaskNumberException, TaskAlreadyDoneException, FileUpdateFailException {
        checkValidity(input, SimpleCommandType.DONE, taskList);
        int digit = Integer.parseInt(input);
        Task current = taskList.get(digit - 1);

        if (current.isDone()) {
            throw new TaskAlreadyDoneException();
        }
        current.markAsDone();
        storage.updateFile(taskList);
        return ui.markTaskAsDone(current);
    }
}
