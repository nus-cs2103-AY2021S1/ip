/**
 * Class to run any other unspecified command.
 * The type of a command is defined as the first word in the command string.
 */
public class UnknownCommand implements Command {

    protected final String COMMAND;

    /**
     * constructor
     */
    public UnknownCommand(String command) {
        this.COMMAND = command;
    }

    /**
     * Executes the unknown command, causing Duke to echo whatever command the user inputs as it is not a valid command.
     *  @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String result = ui.showLine() + "\n" + ui.printUnknownCommandMessage(COMMAND) + ui.showLine();
        return result;
    }

    /**
     * Returns true if a bye command is called.
     * Returns False otherwise.
     *
     * @return boolean indicating whether Duke is to stop running.
     */
    public boolean isExit() {
        return false;
    }
}
