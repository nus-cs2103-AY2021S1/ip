package duke.command;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Class to represents all command. This class also contains
 * specific string for commands.
 */
public abstract class Command {

    /** String to specify bye command. */
    public static String BYE_COMMAND = "bye";

    /** String to specify list command. */
    public static String LIST_COMMAND = "list";

    /** String to specify done command. */
    public static String DONE_COMMAND = "done";

    /** String to specify delete command. */
    public static String DELETE_COMMAND = "delete";

    /** String to specify todo command. */
    public static String TODO_COMMAND = "todo";

    /** String to specify deadline command. */
    public static String DEADLINE_COMMAND = "deadline";

    /** String to specify event command. */
    public static String EVENT_COMMAND = "event";

    /** String to specify taskafter command. */
    public static String TASK_AFTER_COMMAND = "taskafter";

    /** String to specify taskbefore command. */
    public static String TASK_BEFORE_COMMAND = "taskbefore";

    private boolean isExit;

    /**
     * Constructs a Command object with isExit as false.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns whether the Duke should stop running or not.
     *
     * @return Duke's current state to continue running.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Terminates Duke program.
     */
    public void exit() {
        this.isExit = true;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     * @throws DukeException Thrown when something when wrong when executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
