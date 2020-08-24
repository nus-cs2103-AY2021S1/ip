package duke;

/**
 * Represents an exit command in Duke.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printBye();
    }
}
