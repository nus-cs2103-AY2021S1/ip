package commands;

import commands.Command;

/**
 * <code>commands.DeadlineCommand</code> inherits from the base class <code>commands.Command</code>
 * and will handle the job of adding deadlines to the task manager.
 */
public class DeadlineCommand extends Command {
    /**
     * Adds a <code>Deadline</code> to the task manager.
     * Using the <code>duke.Ui</code> object in the parent class, it prints out
     * the user interface to ask for the name and due date of the deadline to be created.
     * It uses the <code>Scanner</code> object in the parent class to receive the name and due date
     * of the deadline.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>add</code> method with a <code>Deadline</code> object passed as an argument.
     * @return <code>true</code>
     * @throws DukeException if the construction of the <code>Deadline</code> object results in an <code>exception</code>
     */
    public boolean execute() throws DukeException {
        ui.askDeadlineName();
        String deadlineName = sc.nextLine();
        ui.askDeadlineDate();
        String deadlineDate = sc.nextLine();
        tm.add(new Deadline(deadlineName, deadlineDate));
        return true;
    }
}