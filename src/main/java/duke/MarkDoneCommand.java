package duke;

/**
 * Command to mark a task as done
 */
public class MarkDoneCommand extends Command {
    private final int taskIndex;
    /**
     * MarkDoneCommand constructor
     *
     * @param taskIndex Index of task to be completed
     */
    public MarkDoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as done, then prints info
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @throws DukeException if exception encountered
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.size() || taskIndex < 0) {
            throw new DukeException("Sorry, the task does not exist :(");
        } else {
            Task completedTask = tasks.markDone(taskIndex);
            storage.updateFile(tasks);
            return ui.printTask(completedTask, ActionType.MARK_DONE);
        }
    }
}
