/**
 * Represents a command to list the tasks saved.
 */
public class ListCommand extends Command {

    /**
     * Public constructor.
     */
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayList(tasks.getList());
    }
}
