package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskManager;

/**
 * <code>DeleteCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of deleting tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Deletes a task.
     * @param input the user input
     * @return <code>true</code>.
     * @throws DukeException if an invalid task number was given by the user.
     */
    @Override
    public boolean execute(String input) throws DukeException {
        Task task = tm.deleteTask(Integer.parseInt(input));
        String response = String.format("Successfully removed the following task:\n %s", task);
        setResponse(response);
        setDone();
        return true;
    }

    /**
     * Sets the utility tools <code>tm</code> and <code>ui</code>.
     * In addition, it sets the initial response to ask for the task number to be deleted.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(ui.askTaskNumToDelete());
    }
}