package duke.command;

import duke.exception.InvalidSimpleCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.tasklist.TaskList;

/**
 * Abstracts the logic of deleting and completing a task.
 */
public abstract class SimpleCommand extends Command {

    /**
     * Checks if the input string from the user is valid by first checking if it is a number, and then checking
     * if the number is within the size of {@code TaskList}.
     *
     * @param input Input string.
     * @param simpleCommandType Simple command type
     * @param taskList TaskList.
     * @throws InvalidSimpleCommandException When the user input is not a number.
     * @throws InvalidTaskNumberException When the number is not within the size of the TaskList.
     */
    protected void checkValidity(String input, SimpleCommandType simpleCommandType, TaskList taskList)
        throws InvalidSimpleCommandException, InvalidTaskNumberException {
        if (!isNumber(input)) {
            throw new InvalidSimpleCommandException(simpleCommandType);
        }

        int digit = Integer.parseInt(input);
        if (!taskList.checkIfValid(digit)) {
            throw new InvalidTaskNumberException(taskList.size());
        }
    }

    /**
     * Checks if the string is a number, returning true if so, false otherwise.
     *
     * @param str String to check.
     * @return True if string is a number, false otherwise.
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
