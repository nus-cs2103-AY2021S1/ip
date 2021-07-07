package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>ExitCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of exiting duke.
 */
public class ExitCommand extends Command {
    /**
     * Returns true.
     * Note that this method won't be called for ExitCommand.
     * @param input the user input.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) {
        return true;
    }

    /**
     * Sets the initial response to be "Goodbye".
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse("Goodbye!"); // TODO: refactor
        setDone();
    }
}