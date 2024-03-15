/**
 * DeleteCommand class that executes delete actions
 * Extends from command class/
 */
public class DeleteCommand extends Command {

    /**
     * Creates a delete command that handles delete actions
     * @param ui ui that handles the user interface
     * @param taskList list of tasks
     * @param args String of delete commands
     */
    DeleteCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for delete command
     * @return String containing a successful delete message
     */
    @Override
    public String action() {
        String result;
        int num = Integer.parseInt(args);
        Task task = taskList.getTask(num);
        taskList.deleteTask(num);
        result = ui.printDelete(taskList, task);
        return result;
    }
}
