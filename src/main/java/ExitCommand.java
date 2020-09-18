/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command to say goodbye to the user and save the tasks.
     * @param tasks list of tasks
     * @param ui user interface to display deleted message
     * @param storage file storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks.getTasksList());
        return ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
