package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.CompleteCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of marking duke.tasks as complete.
 */
public class CompleteCommand extends Command {
    /**
     * Marks a task as complete.
     * Using the <code>duke.Ui</code> object in the parent class, it prints out
     * the user interface to ask for the number of the task to be completed.
     * It uses the <code>Scanner</code> object in the parent class to receive the number.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>markDone</code> method with the task number passed as an argument to mark
     * the task as completed.
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

    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(ui.askTaskNumToComplete());
    }
}