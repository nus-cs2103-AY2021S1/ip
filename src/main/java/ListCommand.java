/**
 * ListCommand class that executes list actions.
 * Extends from the Command class.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     * @param ui ui that handles the user interface
     * @param taskList list of tasks
     * @param args String of list commands
     */
    public ListCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes actions for list command
     * @return String containing the list of tasks
     */
    @Override
    public String action() {
        return ui.printList(taskList);
    }
}
