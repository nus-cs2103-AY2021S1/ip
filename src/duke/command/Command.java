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
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_TASK_AFTER = "taskafter";
    private static final String COMMAND_TASK_BEFORE = "taskbefore";
    private static final String COMMAND_FIND = "find";
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
     * Gets the string representation to start bye command
     *
     * @return String to specify of bye command
     */
    public static String getCommandBye() {
        return COMMAND_BYE;
    }

    /**
     * Gets the string representation to start list command
     *
     * @return String to specify of list command
     */
    public static String getCommandList() {
        return COMMAND_LIST;
    }

    /**
     * Gets the string representation to start done command
     *
     * @return String to specify of done command
     */
    public static String getCommandDone() {
        return COMMAND_DONE;
    }

    /**
     * Gets the string representation to start delete command
     *
     * @return String to specify of delete command
     */
    public static String getCommandDelete() {
        return COMMAND_DELETE;
    }

    /**
     * Gets the string representation to start todo command
     *
     * @return String to specify of todo command
     */
    public static String getCommandTodo() {
        return COMMAND_TODO;
    }

    /**
     * Gets the string representation to start deadline command
     *
     * @return String to specify of deadline command
     */
    public static String getCommandDeadline() {
        return COMMAND_DEADLINE;
    }

    /**
     * Gets the string representation to start event command
     *
     * @return String to specify of event command
     */
    public static String getCommandEvent() {
        return COMMAND_EVENT;
    }

    /**
     * Gets the string representation to start task after command
     *
     * @return String to specify of task after command
     */
    public static String getCommandTaskAfter() {
        return COMMAND_TASK_AFTER;
    }

    /**
     * Gets the string representation to start task before command
     *
     * @return String to specify of task before command
     */
    public static String getCommandTaskBefore() {
        return COMMAND_TASK_BEFORE;
    }

    /**
     * Gets the string representation to start find command
     *
     * @return String to specify of find command
     */
    public static String getCommandFind() {
        return COMMAND_FIND;
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
