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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayList(tasks.getList(), false);
    }
}
