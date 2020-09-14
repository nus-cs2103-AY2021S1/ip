package duke.command;

import duke.exception.FileUpdateFailException;
import duke.exception.InvalidSimpleCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Abstracts the execution of the deletion of tasks command.
 */
public class DeleteCommand extends SimpleCommand {

    private final String input;

    /**
     * Initializes the {@code DeleteCommand} object.
     *
     * @param input Input string from user.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the deletion of task command.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws InvalidSimpleCommandException If Delete command is invalid.
     * @throws InvalidTaskNumberException If task number does not lie within the size of TaskList.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidSimpleCommandException,
        InvalidTaskNumberException, FileUpdateFailException {
        checkValidity(input, SimpleCommandType.DELETE, tasks);
        int digit = Integer.parseInt(input);
        Task current = tasks.get(digit - 1);
        tasks.delete(digit - 1);
        storage.updateFile(tasks);
        return ui.deleteTask(current, tasks.size());
    }
}
