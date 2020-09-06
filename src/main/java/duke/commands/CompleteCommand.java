package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskManager;

/**
 * <code>CompleteCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of marking tasks as complete.
 */
public class CompleteCommand extends Command {
    /**
     * Marks a task as complete.
     * @return <code>true</code>
     * @throws DukeException if an invalid task number was given by the user.
     */
    @Override
    public boolean execute(String input) throws DukeException {
        Task task = tm.markDone(Integer.parseInt(input));
        setResponse(ui.taskCompleted(task));
        setDone();
        return true;
    }

    /**
     * Sets the utility tools <code>tm</code> and <code>ui</code>.
     * In addition, it sets the initial response to ask for the task number
     * to complete.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(ui.askTaskNumToComplete());
    }
}