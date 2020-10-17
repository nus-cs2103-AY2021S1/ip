/**
 * A list command to list all tasks
 */
class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    ListCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    /**
     * Executes the ListCommand
     * @return Returns String of list of tasks
     */
    @Override
    public String execute() {
        return ui.printf("Here are the tasks in your list:\n" + tasks.toString());
    }
}
