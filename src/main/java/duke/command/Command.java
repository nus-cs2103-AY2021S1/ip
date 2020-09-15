package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;


/**
 * Represents an abstract command that the user intends to execute.
 */
public abstract class Command {

    /** String array storing the user input. */
    private final String[] userString;

    /**
     * Creates a new Command and initialises its string array.
     *
     * @param userString Tokenized array form of the input command string.
     */
    Command(String[] userString) {
        this.userString = userString;
    }

    /**
     * Executes the command to add a task, mark a task as done, delete the task, list all tasks or exit.
     * Abstract method that should be implemented in all sub-types of the Command class.
     *
     * @param tasks The task list to operate on.
     * @param ui The user-interaction object responsible for all system printing and user-interaction.
     * @param storage Represents the logic needed to write to an user-specified file.
     * @return String to be printed out.
     * @throws DukeException If the execution of the command fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines if the command should result in the termination of the program.
     *
     * @return True If the program should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the string array.
     *
     * @return The string array.
     */
    protected String[] getStringArray() {
        return userString;
    }
}
