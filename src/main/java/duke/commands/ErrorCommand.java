package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>ErrorCommand</code> inherits from the base class <code>Command</code>
 * and will handle the situation when the user gives an invalid command.
 */
public class ErrorCommand extends Command {

    /**
     * Calls the ui class to output an error message.
     * @param input the user input
     * @return <code>true</code>.
     */
    @Override
    public boolean execute(String input) {
        return true;
    }

    /**
     * Initialises the response with an error message.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setResponse("Oops, you gave an invalid command!");
        setDone();
    }
}