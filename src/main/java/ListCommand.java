/**
 * Represents a list command which allows users to view the 
 * current list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, where the list of tasks will be printed by the ui.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used 
     * @param storage the current Storage object being used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOnList(tasks);
    }

}
