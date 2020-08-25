/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Initializes done command with the index the user inputted.
     * @param index The index of the task which the user wants to mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task from the task list as done and shows the task to the user.
     * Saves the new task list to the disk as well.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskDone(tasks.markTaskDone(index - 1));
        storage.saveTaskList(tasks);
    }
}
