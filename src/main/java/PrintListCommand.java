/**
 * The PrintListCommand class, when executed, prints the contents of the TaskList to the console.
 *
 * @author Jaya Rengam
 */
public class PrintListCommand implements Command {

    private boolean isDone;

    PrintListCommand() {
        this.isDone = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (isDone) {
            throw new CartonaException("Error: PrintListCommand already executed");
        }

        // Print UI message
        ui.printTaskList(taskList);

        // Update Storage
        this.isDone = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
