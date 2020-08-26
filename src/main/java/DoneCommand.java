/**
 * Encapsulates a DoneCommand with an index
 * which specifies the specific task as done.
 */
public class DoneCommand extends Command {
    public int index;
    
    /**
     * Instantiates a DoneCommand object.
     * @param index the unique index of the task.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as done and displays the UI for the done task.
     * @param tasks the current TaskList.
     * @param ui the current Ui.
     * @param storage the current Storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskList().get(index);
        ui.showDone(task);
        task.markAsDone();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
