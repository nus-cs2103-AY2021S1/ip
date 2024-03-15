/**
 * ByeCommand executes Bye action. Extends from Command class.
 */
public class ByeCommand extends Command {

    /**
     * Constructor to create a ByeCommand object.
     * @param ui Ui that handles user interactions.
     * @param taskList list of tasks
     * @param args String of bye commands
     */
    public ByeCommand(Ui ui, TaskList taskList, String args) {
        super(ui, taskList, args);
    }

    /**
     * Executes Bye action
     * @return String containing the Bye message
     */
    @Override
    public String action() {
        return Ui.printBye();
    }
}
