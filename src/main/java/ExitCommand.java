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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks.getTasksList());
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
