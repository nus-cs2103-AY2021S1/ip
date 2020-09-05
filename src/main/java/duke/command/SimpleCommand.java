package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidDeleteException;
import duke.exception.InvalidDoneException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.TaskAlreadyDoneException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Abstracts the logic of deleting and completing a task.
 */
public class SimpleCommand extends Command {

    private static final String INVALID_SIMPLE_COMMAND_MESSAGE = "Command type is neither delete or done";

    private final String input;
    private final SimpleCommandType type;

    /**
     * Initializes the SimpleCommand class with the input string and SimpleCommandType.
     *
     * @param input Input string.
     * @param type SimpleCommandType.
     */
    public SimpleCommand(String input, SimpleCommandType type) {
        this.input = input;
        this.type = type;
    }

    /**
     * Deletes or complete a task, depending on the task type.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws DukeException If an error is found in the user input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!isNumber(input)) {
            if (type == SimpleCommandType.DONE) {
                throw new InvalidDoneException();
            } else if (type == SimpleCommandType.DELETE) {
                throw new InvalidDeleteException();
            } else {
                assert false : INVALID_SIMPLE_COMMAND_MESSAGE;
            }
        }
        int digit = Integer.parseInt(input);
        if (!tasks.checkIfValid(digit)) {
            throw new InvalidTaskNumberException(tasks.size());
        }
        Task current = tasks.get(digit - 1);
        if (type == SimpleCommandType.DONE) {
            if (current.isDone()) {
                throw new TaskAlreadyDoneException();
            }
            current.markAsDone();
            storage.update(tasks);
            return ui.markTaskAsDone(current);
        } else if (type == SimpleCommandType.DELETE) {
            tasks.delete(digit - 1);
            storage.update(tasks);
            return ui.deleteTask(current, tasks.size());
        } else {
            assert false : INVALID_SIMPLE_COMMAND_MESSAGE;
            return null;
        }
    }

    /**
     * Checks if the string is a number, returning true if so, false otherwise.
     *
     * @param str String to check.
     * @return true if string is a number, false otherwise.
     */
    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
