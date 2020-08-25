/**
 * Command to list the current tasks the user has.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by printing the list of tasks.
     * @param tasks the current list of tasks.
     * @param ui Ui object to handle printing.
     * @param storage Storage object.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list!");
        ui.showMessage(tasks.toString());
    }

    /**
     * Returns whether the command is a command to exit.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
