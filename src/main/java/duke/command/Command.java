package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code Command} is an abstract object which wraps the user's instruction into Command objects and execute.
 */
public abstract class Command {
    /** Status of the {@code Duke} programme. True if programme is exited. */
    public static boolean isTerminated = false;

    /**
     * Execute the wrapped instruction.
     * 
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    public abstract void execute(TaskList tasks, Ui ui);
}
