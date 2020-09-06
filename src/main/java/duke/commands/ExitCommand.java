package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.ExitCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of exiting duke.
 */
public class ExitCommand extends Command {
    /**
     * Returns <code>false</code> so that the program will stop running.
     * @return <code>false</code>
     */
    @Override
    public boolean execute(String input) {
        return false;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse("Goodbye!"); // TODO: refactor
        setDone();
    }
}