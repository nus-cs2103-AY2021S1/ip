package commands;

import commands.Command;

/**
 * <code>commands.CompleteCommand</code> inherits from the base class <code>commands.Command</code>
 * and will handle the job of marking tasks as complete.
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
    public boolean execute() throws DukeException {
        ui.askTaskNumToComplete();
        int taskNum = Integer.parseInt(sc.nextLine());
        Task task = tm.markDone(taskNum);
        ui.taskCompleted(task);
        return true;
    }
}