package duke;


/**
 * Command to print list of tasks
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Makes Ui print list of tasks
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
    }
}
