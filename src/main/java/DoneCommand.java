/**
 * DoneCommand class executes done actions.
 * Extends from the Command class.
 */
public class DoneCommand extends Command {

    /**
     * Creates a Done command object
     * @param ui ui that handles user interface
     * @param taskList list of tasks
     * @param args String of done commands
     */
    public DoneCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for done command
     * @return String containing a successful done command
     */
    @Override
    public String action() {
        String result;
        int num = Integer.parseInt(args);
        Task task = taskList.getTask(num);
        task.markAsDone();
        result = ui.printDone(taskList.getTask(num));
        return result;
    }
}
