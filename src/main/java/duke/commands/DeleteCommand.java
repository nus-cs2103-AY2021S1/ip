package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.DeleteCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of deleting duke.tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Deletes a task.
     * Using the <code>duke.Ui</code> object in the parent class, it prints out
     * the user interface to ask for the number of the task to be deleted.
     * It uses the <code>Scanner</code> object in the parent class to receive the number.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>deleteTask</code> method with the task number passed as an argument to delete
     * the task.
     * @return <code>true</code>
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

    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(ui.askTaskNumToComplete());
    }
}