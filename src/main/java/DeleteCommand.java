/**
 * <code>DeleteCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of deleting tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Deletes a task.
     * Using the <code>Ui</code> object in the parent class, it prints out
     * the user interface to ask for the number of the task to be deleted.
     * It uses the <code>Scanner</code> object in the parent class to receive the number.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>deleteTask</code> method with the task number passed as an argument to delete
     * the task.
     * @return <code>true</code>
     * @throws DukeException if an invalid task number was given by the user.
     */
    public boolean execute() throws DukeException {
        ui.askTaskNumToDelete();
        int taskNum = Integer.parseInt(sc.nextLine());
        tm.deleteTask(taskNum);
        return true;
    }
}