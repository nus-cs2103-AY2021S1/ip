package duke.commands;

/**
 * <code>duke.commands.ListCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the job of listing duke.tasks.
 */
public class ListCommand extends Command {
    /**
     * Calls the <code>listTasks</code> method of the <code>TaskManager</code>
     * in the parent class. This returns a <code>String</code> which is then
     * printed out using the <code>print</code> method of the <code>duke.Ui</code>
     * object in the parent class.
     * @return <code>true</code>
     */
    public boolean execute() {
        String s = tm.listTasks();
        ui.print(s);
        return true;
    }
}