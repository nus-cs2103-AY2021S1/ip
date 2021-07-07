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
        try {
            Task task = tm.deleteTask(Integer.parseInt(input));
            String response = String.format("Successfully removed the following task:\n %s", task);
            setResponse(response);
            setDone();
            return true;
        } catch (NumberFormatException e) {
            throw new DukeException("I couldn't understand your input. Please give a number.");
        }

    }

    /**
     * Sets the utility tools <code>tm</code> and <code>ui</code>.
     * Checks if there are any tasks in the task manager. If there are tasks, this will set the response to ask
     * for a task number to delete.
     * If not, then the command will be set as done and set a response indicating that there are no tasks to delete.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        if (tm.isEmpty()) {
            setDone();
            setResponse("The task list is empty.");
        } else {
            setResponse(ui.askTaskNumToDelete());
        }
    }
}