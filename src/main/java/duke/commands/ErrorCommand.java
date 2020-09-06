package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.ErrorCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the situation when the user gives an invalid command.
 */
public class ErrorCommand extends Command {

    @Override
    public boolean execute(String input) {
        // TODO: refactor this to the ui class
        return true;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse("Oops, you gave an invalid command!");
        setDone();
    }
}