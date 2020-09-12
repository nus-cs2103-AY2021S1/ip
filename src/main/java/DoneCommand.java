/**
 * <p>DoneCommand class defines the behaviour of a command to mark a task as done.</p>
 */
public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        assert (taskIndex >= 1) :  "Task index must be more than 0";
        ui.replyDone(taskManager.getTask(taskIndex - 1));
        taskManager.markTaskAsDone(taskIndex - 1);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
