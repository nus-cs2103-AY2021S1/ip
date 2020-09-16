package duke;

/**
 * Returns tasks in TaskList.
 */
public class ListCommand extends Command {
    /**
     * Executes command by displaying tasks in the TaskList.
     *
     * @param tasks   TaskList containing the tasks.
     * @param storage To read and write to file.
     * @param ui      Interact with user.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTasksList(tasks);
    }
}
