package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Arrays;

/**
 * Represents an abstract command that the user intends to execute.
 */
public abstract class Command {

    /** String array storing the user input. */
    private String[] stringArray;

    /**
     * Creates a new Command and initialises its string array.
     *
     * @param stringArray Tokenized array form of the input command string.
     */
    Command(String[] stringArray) {
        this.stringArray = stringArray;
    }

    /**
     * Returns if the first index of the string array variable is empty.
     *
     * @return True if the first index of the string array variable is empty.
     */
    public boolean isFirstIndexEmpty() {
        return stringArray.length < 2 || stringArray[1].equals("");
    }

    /**
     * Executes the command to add a task, mark a task as done, delete the task, list all tasks or exit.
     * Abstract method that should be implemented in all sub-types of the Command class.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @throws DukeException If the execution of the command fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines if the command should result in the termination of the program.
     *
     * @return True If the program should exit.
     */
    public boolean isExit() {
        return false;
    };

    /**
     * Checks if the input string is contained within the string array of the AddCommand object.
     *
     * @param delimiter Input string to be checked against the string array.
     * @return True if the string is found inside of the string array.
     */
    public boolean containsString(String delimiter) {
        return Arrays.stream(getArray()).anyMatch(delimiter::equals);
    }

    /**
     * Returns the item contained in the first index of the string array.
     *
     * @return The the item contained in the first index of the string array.
     */
    public String getFirstIndex() {
        return getArray()[1];
    }

    /**
     * Returns the string array.
     * @return The string array.
     */
    protected String[] getArray() {
        return this.stringArray;
    }
}
