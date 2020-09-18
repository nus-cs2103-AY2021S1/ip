/**
 * Class to run the bye command.
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command, causing Duke to stop running.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
        ui.printGoodbyeMessage();
        ui.showLine();
    }

    /**
     * Returns true if a bye command is called.
     * Returns False otherwise.
     *
     * @return boolean indicating whether Duke is to stop running.
     */
    public boolean isExit() {
        return true;
    }
}
