package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;

/**
 * <code>ListCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of listing tasks.
 */
public class ListCommand extends Command {
    /**
     * Returns true since there is only one initial response which has been set in
     * the method call <code>init</code>.
     * @return <code>true</code>
     */
    @Override
    public boolean execute(String input) {
        return true;
    }

    /**
     * Sets the initial response to be the list of tasks.
     * In addition, it Sets the utility tools <code>tm</code> and <code>ui</code>.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(tm.listTasks());
        setDone();
    }
}